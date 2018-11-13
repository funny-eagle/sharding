package com.redocon.ebook.mapper;

import com.redocon.ebook.entity.LibraryEbook;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface LibraryEbookMapper {

    @Insert("insert into ebook.library_ebook(order_id, customer_id, library_id, ebook_id, status) values (#{orderId}, #{customerId}, #{libraryId}, #{ebookId}, #{status})")
    int insert(@Param("orderId") Integer orderId, @Param("customerId") Integer customerId, @Param("libraryId") Integer libraryId, @Param("ebookId") Integer ebookId, @Param("status") Integer status);

    @Insert({
            "<script>",
            "insert into ebook.library_ebook(order_id, customer_id, library_id, ebook_id, status)",
            "values ",
            "<foreach  collection='libraryEbookList' item='libraryEbook' separator=','>",
            "( #{libraryEbook.orderId}, #{libraryEbook.customerId}, #{libraryEbook.libraryId}, #{libraryEbook.ebookId}, #{libraryEbook.status})",
            "</foreach>",
            "</script>"
    })
    int insertBatch(@Param("libraryEbookList") List<LibraryEbook> libraryEbookList);

    @Select("select count(*) from ebook.library_ebook where order_id=#{orderId} and customer_id=#{customerId} and library_id=#{libraryId} and ebook_id=#{ebookId}")
    int count(@Param("orderId") Integer orderId, @Param("customerId") Integer customerId, @Param("libraryId") Integer libraryId, @Param("ebookId") Integer ebookId);
}