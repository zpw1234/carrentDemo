package com.sxt.bus.mapper;

import com.sxt.bus.domain.Customer;
import com.sxt.bus.domain.CustomerExample;
import java.util.List;

import com.sxt.bus.vo.CustomerVo;
import org.apache.ibatis.annotations.Param;

public interface CustomerMapper {
    long countByExample(CustomerExample example);

    int deleteByExample(CustomerExample example);

    int deleteByPrimaryKey(String identity);

    int insert(Customer record);

    int insertSelective(Customer record);

    List<Customer> selectByExample(CustomerExample example);

    Customer selectByPrimaryKey(String identity);

    int updateByExampleSelective(@Param("record") Customer record, @Param("example") CustomerExample example);

    int updateByExample(@Param("record") Customer record, @Param("example") CustomerExample example);

    int updateByPrimaryKeySelective(Customer record);

    int updateByPrimaryKey(Customer record);

    /**
     * 查询所有客户
     * @param customerVo
     * @return
     */
    List<Customer> queryAllCustomer(CustomerVo customerVo);
}