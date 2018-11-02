package com.redocon.shardingjdbc.mapper;

import com.redocon.shardingjdbc.entity.Library;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface LibraryMapper {

    @Select("select * from ebook.library where customer_id=#{customerId}")
    @Results({
            @Result(property = "customerId", column = "customer_id")
    })
    public List<Library> selectByCustomerId(@Param("customerId") Integer customerId);

    @Insert("insert into ebook.library(customer_id, name) values (#{customerId}, #{name})")
    public void insert(@Param("customerId") Integer customerId, @Param("name") String name);
}
