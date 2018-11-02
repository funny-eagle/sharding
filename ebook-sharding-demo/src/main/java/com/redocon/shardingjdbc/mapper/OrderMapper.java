package com.redocon.shardingjdbc.mapper;

import com.redocon.shardingjdbc.entity.Order;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface OrderMapper {

    @Insert("INSERT INTO ebook.order(customer_id, name) VALUES (#{customerId}, #{name})")
    public void insert(@Param("customerId") Integer customerId, @Param("name") String name);

    @Select("select * from ebook.order where id=#{id}")
    @Results({@Result(property = "customerId", column = "customer_id")})
    Order selectById(@Param("id") Integer orderId);
}
