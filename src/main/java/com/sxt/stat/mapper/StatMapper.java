package com.sxt.stat.mapper;

import com.sxt.stat.domain.BaseEntity;

import java.util.List;

public interface StatMapper {
    //查询客户地区的数据
    List<BaseEntity> queryCustomerAreaStat();

    //性别统计
    List<BaseEntity> queryCustomerAreaSexStat(String area);

    List<BaseEntity> queryOpernameYearGradeStat(String year);

    List<Double> queryCompanyYearGradeStat(String year);
}
