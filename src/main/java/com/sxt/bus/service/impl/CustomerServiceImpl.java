package com.sxt.bus.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sxt.bus.domain.Customer;
import com.sxt.bus.mapper.CustomerMapper;
import com.sxt.bus.service.CustomerService;
import com.sxt.bus.vo.CustomerVo;
import com.sxt.sys.utils.DataGridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerMapper customerMapper;

    /**
     * 查询所有客户
     * @param customerVo
     * @return
     */
    @Override
    public DataGridView queryAllCustomer(CustomerVo customerVo) {
        Page<Object> page = PageHelper.startPage(customerVo.getPage(),customerVo.getLimit());
        List<Customer> data = this.customerMapper.queryAllCustomer(customerVo);
        return new DataGridView(page.getTotal(),data);
    }

    /**
     * 删除用户
     */
    public void deleteCustomer(String identity){
        this.customerMapper.deleteByPrimaryKey(identity);
    }

    /**
     * 添加用户
     */
    public void addCustomer(CustomerVo customerVo){
        this.customerMapper.insertSelective(customerVo);
    }

    /**
     * 修改用户
     */
    public void updateCustomer(CustomerVo customerVo){
        this.customerMapper.updateByPrimaryKeySelective(customerVo);
    }

    /**
     * 批量删除
     */
    public void deleteBatchCustomer(String[] ids){
        for (String id : ids){
            deleteCustomer(id);
        }
    }

    @Override
    public Customer queryCustomerByIdentity(String identity) {
        return this.customerMapper.selectByPrimaryKey(identity);
    }

    @Override
    public List<Customer> queryAllCustomerForList(CustomerVo customerVo) {
        return this.customerMapper.queryAllCustomer(customerVo);
    }


}
