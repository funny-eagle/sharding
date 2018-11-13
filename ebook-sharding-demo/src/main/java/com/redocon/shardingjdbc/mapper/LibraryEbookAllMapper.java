package com.redocon.shardingjdbc.mapper;

import com.redocon.shardingjdbc.entity.LibraryEbook;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface LibraryEbookAllMapper {

    @Select({"select id, order_id, library_id, customer_id, ebook_id, status from library_ebook_2 limit #{pageNum}, #{sizePerPage}"})
    @Results({
            @Result(property = "customerId", column = "customer_id"),
            @Result(property = "libraryId", column = "library_id"),
            @Result(property = "ebookId", column = "ebook_id"),
            @Result(property = "orderId", column = "order_id")
    })
    List<LibraryEbook> selectSharding(@Param("pageNum") Integer pageNum, @Param("sizePerPage") Integer sizePerPage);

    @Insert({
            "<script>",
            "insert into ebook.library_ebook_all(order_id, customer_id, library_id, ebook_id, status)",
            "values ",
            "<foreach  collection='libraryEbookList' item='libraryEbook' separator=','>",
            "( #{libraryEbook.orderId}, #{libraryEbook.customerId}, #{libraryEbook.libraryId}, #{libraryEbook.ebookId}, #{libraryEbook.status})",
            "</foreach>",
            "</script>"
    })
    int insertBatch(@Param("libraryEbookList") List<LibraryEbook> libraryEbookList);


    @Select("select count(*) from library_ebook_all where order_id=#{orderId} and customer_id=#{customerId} and library_id=#{libraryId} and ebook_id=#{ebookId}")
    int count(@Param("orderId") Long orderId, @Param("customerId") Long customerId, @Param("libraryId") Long libraryId, @Param("ebookId") Long ebookId);

    @Delete("DELETE FROM library_ebook_all where order_id=#{orderId}")
    void deleteByOrderId(@Param("orderId") Long orderId);

    @Delete("truncate table library_ebook_all")
    void truncate();

    @Select("select * from library_ebook_all where order_id=#{orderId} limit #{pageNum}, #{sizePerPage}")
    List<Map> selectByOrderId(@Param("orderId") Integer orderId, @Param("pageNum") Integer pageNum, @Param("sizePerPage") Integer sizePerPage);

    @Select("select * from library_ebook_all where library_id=#{libraryId} limit #{pageNum}, #{sizePerPage}")
    List<Map> selectByLibraryId(@Param("libraryId") Integer libraryId, @Param("pageNum") Integer pageNum, @Param("sizePerPage") Integer sizePerPage);

    @Select("select * from library_ebook_all where customer_id=#{customerId} limit #{pageNum}, #{sizePerPage}")
    List<Map> selectByCustomerId(@Param("customerId") Integer customerId, @Param("pageNum") Integer pageNum, @Param("sizePerPage") Integer sizePerPage);
}