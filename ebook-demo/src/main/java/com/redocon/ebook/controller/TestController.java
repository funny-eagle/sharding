package com.redocon.ebook.controller;

import com.redocon.ebook.entity.*;
import com.redocon.ebook.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
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
    //@PostConstruct
    public String addLibraryEbook(){
        Integer orderId = 3;
        // 根据orderId找到customerId
        // Order order = orderMapper.selectById(orderId);
        // 根据orderId找到libraryId
        List<OrderLibrary> orderLibraryList = orderLibraryMapper.selectByOrderId(orderId);
        // 根据orderId找ebookId
        List<OrderEbook> orderEbookList = orderEbookMapper.selectByOrderId(orderId);

        List<LibraryEbook> libraryEbookList = new ArrayList<>();
        // 将所有的电子书配发到客户下所有的图书馆
        orderLibraryList.forEach(orderLibrary -> {
            orderEbookList.forEach(orderEbook -> {
                // libraryEbookMapper.count(orderId, order.getCustomerId(), orderLibrary.getLibraryId(), orderEbook.getEbookId()) == 0
                // fixme: 避免插入重复数据，运行之前查一下，目前最大的library_id
                if(orderLibrary.getLibraryId() > 25){
                    LibraryEbook libraryEbook = new LibraryEbook();
                    libraryEbook.setOrderId(orderId);
                    libraryEbook.setCustomerId(3);
                    libraryEbook.setLibraryId(orderLibrary.getLibraryId());
                    libraryEbook.setEbookId(orderEbook.getEbookId());
                    libraryEbook.setStatus(1);
                    libraryEbookList.add(libraryEbook);
                }
            });
        });
        long start = System.currentTimeMillis();
        System.out.println("insert batch start : " + start);
        libraryEbookMapper.insertBatch(libraryEbookList);
        long end = System.currentTimeMillis();
        System.out.println("insert batch end : " + end);
        System.out.println("insert batch total millis: " + (end - start));
        return "add library ebook complete!";
    }

    @PostMapping("/testInsertBatchLibraryEbook")
    public String testInsertBatchLibraryEbook(){
        long start = System.currentTimeMillis();
        System.out.println("insert batch start : " + start);
        List<LibraryEbook> libraryEbookList = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            LibraryEbook libraryEbook = new LibraryEbook();
            libraryEbook.setOrderId(99999);
            libraryEbook.setCustomerId(3);
            libraryEbook.setLibraryId(i);
            libraryEbook.setEbookId(i);
            libraryEbook.setStatus(999);
            libraryEbookList.add(libraryEbook);
        }

        libraryEbookMapper.insertBatch(libraryEbookList);
        long end = System.currentTimeMillis();
        System.out.println("insert batch end : " + end);
        System.out.println("insert batch total millis: " + (end - start));
        return "add library ebook complete!";
    }

    @PostMapping("/testInsertLibraryEbook")
    public String testInsertLibraryEbook(){
        long start = System.currentTimeMillis();
        System.out.println("insert start : " + start);
        for (int i = 0; i < 1000; i++) {
            libraryEbookMapper.insert(99999, 3, i, i, 999);
        }
        long end = System.currentTimeMillis();
        System.out.println("insert end : " + end);
        System.out.println("insert total millis: " + (end - start));
        return "add library ebook complete!";
    }
}
