package com.redocon.shardingjdbc.mapper;

import com.redocon.shardingjdbc.entity.OrderEbook;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrderEbookMapper {

    @Insert("INSERT INTO order_ebook(ebook_id, customer_id, order_id) VALUES (#{ebookId}, #{customerId}, #{orderId})")
    public void insert(@Param("ebookId") Long ebookId, @Param("customerId") Long customerId, @Param("orderId") Long orderId);

    @Select("select * from order_ebook where order_id=#{orderId}")
    @Results({
            @Result(property = "customerId", column = "customer_id"),
            @Result(property = "ebookId", column = "ebook_id"),
            @Result(property = "orderId", column = "order_id"),
    })
    List<OrderEbook> selectByOrderId(@Param("orderId") Long orderId);

    @Delete("truncate table order_ebook")
    void truncate();
}
