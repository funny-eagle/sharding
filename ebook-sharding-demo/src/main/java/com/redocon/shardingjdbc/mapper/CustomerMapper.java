package com.redocon.shardingjdbc.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CustomerMapper {
    
    @Insert("insert into ebook.customer(name) values (#{name})")
    public void insert(@Param("name") String name);
}
