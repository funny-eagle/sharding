package com.redocon.shardingjdbc.mapper;

import com.redocon.shardingjdbc.entity.Order;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrderMapper {

    @Insert("INSERT INTO orders(customer_id, name) VALUES (#{customerId}, #{name})")
    public void insert(@Param("customerId") Long customerId, @Param("name") String name);

    @Select("select * from orders where id=#{id}")
    @Results({@Result(property = "customerId", column = "customer_id")})
    Order selectById(@Param("id") Long orderId);

    @Delete("truncate table orders")
    void truncate();

    @Select("select * from orders")
    @Results({
            @Result(property = "customerId", column = "customer_id")
    })
    List<Order> select();
}
