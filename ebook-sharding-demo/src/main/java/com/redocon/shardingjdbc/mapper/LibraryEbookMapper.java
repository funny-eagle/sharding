package com.redocon.shardingjdbc.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface LibraryEbookMapper {

    @Insert("insert into library_ebook(order_id, customer_id, library_id, ebook_id, status) value (#{orderId}, #{customerId}, #{libraryId}, #{ebookId}, #{status})")
    int insert(@Param("orderId") Long orderId, @Param("customerId") Long customerId, @Param("libraryId") Long libraryId, @Param("ebookId") Long ebookId, @Param("status") Integer status);

    @Select("select count(*) from library_ebook where order_id=#{orderId} and customer_id=#{customerId} and library_id=#{libraryId} and ebook_id=#{ebookId}")
    int count(@Param("orderId") Long orderId, @Param("customerId") Long customerId, @Param("libraryId") Long libraryId, @Param("ebookId") Long ebookId);

    @Delete("DELETE FROM library_ebook where order_id=#{orderId}")
    void deleteByOrderId(@Param("orderId") Long orderId);

    @Delete("truncate table library_ebook")
    void truncate();
}