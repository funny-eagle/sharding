package com.redocon.shardingjdbc.mapper;

import com.redocon.shardingjdbc.entity.Customer;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CustomerMapper {
    
    @Insert("insert into customer(name) values (#{name})")
    public void insert(@Param("name") String name);

    @Delete("truncate table customer")
    void truncate();

    @Select("select * from customer")
    List<Customer> select();
}
