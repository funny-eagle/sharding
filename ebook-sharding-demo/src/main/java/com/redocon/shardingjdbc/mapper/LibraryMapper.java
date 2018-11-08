package com.redocon.shardingjdbc.mapper;

import com.redocon.shardingjdbc.entity.Library;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface LibraryMapper {

    @Select("select * from library where customer_id=#{customerId}")
    @Results({
            @Result(property = "customerId", column = "customer_id")
    })
    public List<Library> selectByCustomerId(@Param("customerId") Long customerId);

    @Insert("insert into library(customer_id, name) values (#{customerId}, #{name})")
    public void insert(@Param("customerId") Long customerId, @Param("name") String name);

    @Delete("truncate table library")
    void truncate();
}
