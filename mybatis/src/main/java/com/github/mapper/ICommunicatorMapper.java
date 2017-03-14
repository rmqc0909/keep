package com.github.mapper;

import com.github.bean.Communicator;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by xiedan on 2017/3/2.
 */
public interface ICommunicatorMapper {
    @Select("SELECT * FROM communicator WHERE communicator_id=#{id}")
    Communicator getById(@Param(value = "id") int id);

    @Select("SELECT * FROM communicator ORDER BY communicator_id")
    List<Communicator> getAll();

}
