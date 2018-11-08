package com.redocon.ebook.mapper;

import com.redocon.ebook.entity.Ebook;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface EbookMapper {

    @Insert("insert into ebook (name) value (#{name})")
    public void insert(@Param("name") String name);

    @Select("select * from ebook")
    List<Ebook> select();
}
