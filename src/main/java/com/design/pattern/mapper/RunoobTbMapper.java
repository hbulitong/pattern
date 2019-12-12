package com.design.pattern.mapper;

import com.design.pattern.vo.RunoobTb;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface RunoobTbMapper {
    @Select("select * from runoob_tb where runoob_id=#{id}")
    RunoobTb getById(int id);
}
