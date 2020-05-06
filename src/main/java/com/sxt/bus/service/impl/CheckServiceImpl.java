package com.sxt.bus.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sxt.bus.domain.Car;
import com.sxt.bus.domain.Check;
import com.sxt.bus.domain.Customer;
import com.sxt.bus.domain.Rent;
import com.sxt.bus.mapper.CarMapper;
import com.sxt.bus.mapper.CheckMapper;
import com.sxt.bus.mapper.CustomerMapper;
import com.sxt.bus.mapper.RentMapper;
import com.sxt.bus.service.CheckService;
import com.sxt.bus.vo.CheckVo;
import com.sxt.sys.constast.SysConstast;
import com.sxt.sys.domain.User;
import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.utils.RandomUtils;
import com.sxt.sys.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CheckServiceImpl implements CheckService {
    @Autowired
    private CheckMapper checkMapper;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private RentMapper rentMapper;

    @Autowired
    private CarMapper carMapper;

    /**
     * 根据出租单号加载检测单的表单数据
     * @param rentid
     * @return
     */
    public Map<String,Object> initCheckFormData(String rentid){
        //查询出租单号
        Rent rent = this.rentMapper.selectByPrimaryKey(rentid);
        //查询客户
        Customer customer = this.customerMapper.selectByPrimaryKey(rent.getIdentity());
        //查询车辆
        Car car = this.carMapper.selectByPrimaryKey(rent.getCarnumber());

        //组装check
        Check check = new Check();
        check.setCheckid(RandomUtils.createRandomStringUseTime(SysConstast.CAR_ORDER_JC));
        check.setRentid(rentid);
        check.setCheckdate(new Date());
        User user =(User) WebUtils.getHttpSession().getAttribute("user");
        check.setOpername(user.getRealname());
        Map<String, Object> map = new HashMap<>();
        map.put("rent",rent);
        map.put("customer",customer);
        map.put("car",car);
        map.put("check",check);

        return map;
    }

    @Override
    public DataGridView queryAllCheck(CheckVo checkVo) {
        Page<Object> page = PageHelper.startPage(checkVo.getPage(),checkVo.getLimit());
        List<Check> data = this.checkMapper.queryAllCheck(checkVo);
        return new DataGridView(page.getTotal(),data);
    }

    @Override
    public void deleteCheck(CheckVo checkVo) {
        this.checkMapper.deleteByPrimaryKey(checkVo.getCheckid());
    }

    @Override
    public void addCheck(CheckVo checkVo) {
        this.checkMapper.insertSelective(checkVo);
        //更改出租单的状态
        Rent rent = this.rentMapper.selectByPrimaryKey(checkVo.getRentid());
        //更改为已归还
        rent.setRentflag(SysConstast.RENT_BACK_TRUE);
        this.rentMapper.updateByPrimaryKeySelective(rent);
        //更改汽车状态
        Car car = this.carMapper.selectByPrimaryKey(rent.getCarnumber());
        //更改汽车状态为未出租
        car.setIsrenting(SysConstast.RENT_CAR_FALSE);
        this.carMapper.updateByPrimaryKeySelective(car);
    }

    @Override
    public void updateCheck(CheckVo checkVo) {
        this.checkMapper.updateByPrimaryKeySelective(checkVo);
    }

    @Override
    public void deleteBatchCheck(String[] ids) {
        for (String id : ids){
            this.checkMapper.deleteByPrimaryKey(id);
        }
    }
}
