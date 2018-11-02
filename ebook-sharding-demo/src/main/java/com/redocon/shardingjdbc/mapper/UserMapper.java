package com.redocon.shardingjdbc.mapper;


import com.redocon.shardingjdbc.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface UserMapper {

    @Insert("insert into user (city, name) value (#{city}, #{name})")
    Long addUser(@Param("city") String city, @Param("name") String name);

    @Select("select * from user")
    List<User> list();

    @Select("select * from user where id=#{id}")
    User findById(@Param("id") Long id);

    @Select("select * from user where name=#{name}")
    User findByName(@Param("name") String name);


    @Select("select * from user as u, customer as c where u.id=c.id and u.name=#{name}")
    Map<String, Object> findCustomer(@Param("name") String name);
}
