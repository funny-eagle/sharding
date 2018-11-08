package com.redocon.ebook.mapper;

import com.redocon.ebook.entity.OrderLibrary;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrderLibraryMapper {

    @Insert("INSERT INTO ebook.order_library(customer_id, library_id, order_id) VALUES (#{customerId}, #{libraryId}, #{orderId})")
    public void insert(@Param("customerId") Integer customerId, @Param("libraryId") Integer libraryId, @Param("orderId") Integer orderId);

    @Select("select * from ebook.order_library where order_id=#{orderId}")
    @Results({
            @Result(property = "customerId", column = "customer_id"),
            @Result(property = "libraryId", column = "library_id"),
            @Result(property = "orderId", column = "order_id"),
    })
    List<OrderLibrary> selectByOrderId(@Param("orderId") Integer orderId);
}
