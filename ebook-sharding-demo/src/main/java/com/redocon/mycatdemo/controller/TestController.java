package com.redocon.mycatdemo.controller;

import com.redocon.mycatdemo.entity.*;
import com.redocon.mycatdemo.mapper.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private LibraryMapper libraryMapper;

    @Autowired
    private EbookMapper ebookMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderEbookMapper orderEbookMapper;

    @Autowired
    private OrderLibraryMapper orderLibraryMapper;

    @Autowired
    private LibraryEbookMapper libraryEbookMapper;

    @PostMapping("/addCustomer")
    public void addCustomer() {
        for (int i = 1; i <= 100; i++) {
            customerMapper.insert("客户" + i);
        }
    }

    @PostMapping("/addLibrarys/{customerId}")
    public void addLibrarys(@PathVariable("customerId") Integer customerId) {
        for (int i = 1; i <= 1000; i++) {
            libraryMapper.insert(customerId, "图书馆" + i);
        }
    }

    @PostMapping("/addEbooks")
    public String addEbooks() {
        for (int i = 1; i <= 20000; i++) {
            ebookMapper.insert("电子书" + i);
        }
        return "complete!";
    }

    @PostMapping("/addOrder/{customerId}")
    public String addOrder(@PathVariable Integer customerId) {
        orderMapper.insert(customerId, "订单" + customerId);
        return "add order complete!";
    }

    @PostMapping("addOrderEbooksAndLibraries/{customerId}/{orderId}")
    public String addOrderEbook(@PathVariable Integer customerId, @PathVariable Integer orderId) {
        // 查询所有的电子书
        List<Ebook> ebookList = ebookMapper.select();
        // 插入order_ebook表
        ebookList.forEach(ebook -> orderEbookMapper.insert(ebook.getId(), customerId, orderId));
        System.out.println("add order ebooks success!");

        // 查询客户下所有的图书馆
        List<Library> libraryList = libraryMapper.selectByCustomerId(customerId);
        // 插入order_library
        libraryList.forEach(library -> orderLibraryMapper.insert(customerId, library.getId(), orderId));
        System.out.println("add order libraries success!");

        return "add order ebooks and libraries complete!";
    }

    @PostMapping("/addLibraryEbook/{orderId}")
    public String addLibraryEbook(@PathVariable Integer orderId){
        // 根据orderId找到customerId
        Order order = orderMapper.selectById(orderId);

        // 根据orderId找到libraryId
        List<OrderLibrary> orderLibraryList = orderLibraryMapper.selectByOrderId(orderId);

        // 根据orderId找ebookId
        List<OrderEbook> orderEbookList = orderEbookMapper.selectByOrderId(orderId);

        // 将所有的电子书配发到客户下所有的图书馆
        orderLibraryList.forEach(orderLibrary -> {
            orderEbookList.forEach(orderEbook -> {
                // libraryEbookMapper.count(orderId, order.getCustomerId(), orderLibrary.getLibraryId(), orderEbook.getEbookId()) == 0
                libraryEbookMapper.insert(orderId, order.getCustomerId(), orderLibrary.getLibraryId(), orderEbook.getEbookId(), 1);
            });
        });



        return "add library ebook complete!";
    }


}
