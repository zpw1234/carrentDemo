package com.sxt.bus.mapper;

import com.sxt.bus.domain.Rent;
import com.sxt.bus.domain.RentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RentMapper {
    long countByExample(RentExample example);

    int deleteByExample(RentExample example);

    int deleteByPrimaryKey(String rentid);

    int insert(Rent record);

    int insertSelective(Rent record);

    List<Rent> selectByExample(RentExample example);

    Rent selectByPrimaryKey(String rentid);

    int updateByExampleSelective(@Param("record") Rent record, @Param("example") RentExample example);

    int updateByExample(@Param("record") Rent record, @Param("example") RentExample example);

    int updateByPrimaryKeySelective(Rent record);

    int updateByPrimaryKey(Rent record);

    public List<Rent> queryAllRent(Rent rent);
}