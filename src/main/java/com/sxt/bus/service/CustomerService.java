package com.sxt.bus.service;

import com.sxt.bus.domain.Customer;
import com.sxt.bus.vo.CustomerVo;
import com.sxt.sys.utils.DataGridView;

import java.util.List;

public interface CustomerService {
    /**
     * 查询所有客户
     * @param customerVo
     * @return
     */
    public DataGridView queryAllCustomer(CustomerVo customerVo);

    /**
     * 删除用户
     * @param customerVo
     */
    void deleteCustomer(String identity);

    /**
     * 添加用户
     * @param customerVo
     */
    void addCustomer(CustomerVo customerVo);

    /**
     * 修改用户
     * @param customerVo
     */
    void updateCustomer(CustomerVo customerVo);

    /**
     * 批量删除
     * @param
     */
    void deleteBatchCustomer(String[] identity);

    /**
     * 根据身份证查询客户信息
     * @param identity
     * @return
     */
    public Customer queryCustomerByIdentity(String identity);

    List<Customer> queryAllCustomerForList(CustomerVo customerVo);
}
