package com.redocon.ebook.mapper;

import com.redocon.ebook.entity.OrderEbook;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrderEbookMapper {

    @Insert("INSERT INTO ebook.order_ebook(ebook_id, customer_id, order_id) VALUES (#{ebookId}, #{customerId}, #{orderId})")
    public void insert(@Param("ebookId") Integer ebookId, @Param("customerId") Integer customerId, @Param("orderId") Integer orderId);

    @Select("select * from ebook.order_ebook where order_id=#{orderId}")
    @Results({
            @Result(property = "customerId", column = "customer_id"),
            @Result(property = "ebookId", column = "ebook_id"),
            @Result(property = "orderId", column = "order_id"),
    })
    List<OrderEbook> selectByOrderId(@Param("orderId") Integer orderId);

}
