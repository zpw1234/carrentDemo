package com.sxt.stat.service;

import com.sxt.stat.domain.BaseEntity;

import java.util.List;

public interface StatService {
    /**
     * 查询客户地区数据
     * @return
     */
    List<BaseEntity> loadCustomerAreaStatList();

    /**
     * 性别统计
     * @param area
     * @return
     */
    List<BaseEntity> loadCustomerAreaSexStatList(String area);

    /**
     * 业务员销售
     * @param year
     * @return
     */
    List<BaseEntity> loadOpernameYearGradeStatList(String year);

    List<Double> loadCompanyYearGradeStatList(String year);
}
