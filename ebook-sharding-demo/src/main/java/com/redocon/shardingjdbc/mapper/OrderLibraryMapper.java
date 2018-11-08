package com.redocon.shardingjdbc.mapper;

import com.redocon.shardingjdbc.entity.OrderLibrary;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrderLibraryMapper {

    @Insert("INSERT INTO order_library(customer_id, library_id, order_id) VALUES (#{customerId}, #{libraryId}, #{orderId})")
    public void insert(@Param("customerId") Long customerId, @Param("libraryId") Long libraryId, @Param("orderId") Long orderId);

    @Select("select * from order_library where order_id=#{orderId}")
    @Results({
            @Result(property = "customerId", column = "customer_id"),
            @Result(property = "libraryId", column = "library_id"),
            @Result(property = "orderId", column = "order_id"),
    })
    List<OrderLibrary> selectByOrderId(@Param("orderId") Long orderId);

    @Delete("truncate table order_library")
    void truncate();
}
