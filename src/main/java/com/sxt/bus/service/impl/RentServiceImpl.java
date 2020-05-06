package com.sxt.bus.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sxt.bus.domain.Car;
import com.sxt.bus.domain.Rent;
import com.sxt.bus.mapper.CarMapper;
import com.sxt.bus.mapper.RentMapper;
import com.sxt.bus.service.RentService;
import com.sxt.bus.vo.RentVo;
import com.sxt.sys.constast.SysConstast;
import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.utils.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentServiceImpl implements RentService {
    @Autowired
    private RentMapper rentMapper;

    @Autowired
    private CarMapper carMapper;

    @Override
    public DataGridView queryAllRent(RentVo rentVo) {
        Page<Object> page = PageHelper.startPage(rentVo.getPage(),rentVo.getLimit());
        List<Rent> data = this.rentMapper.queryAllRent(rentVo);
        return new DataGridView(page.getTotal(),data);
    }

    @Override
    public void deleteRent(String rentid) {
        //更改汽车状态
        Rent rent = this.rentMapper.selectByPrimaryKey(rentid);
        Car car = new Car();
        car.setCarnumber(rent.getCarnumber());
        //转成未出租
        car.setIsrenting(SysConstast.RENT_CAR_FALSE);
        carMapper.updateByPrimaryKeySelective(car);
        this.rentMapper.deleteByPrimaryKey(rentid);
    }

    @Override
    public void addRent(RentVo rentVo) {
        this.rentMapper.insertSelective(rentVo);
        Car car = new Car();
        car.setCarnumber(rentVo.getCarnumber());
        car.setIsrenting(SysConstast.RENT_CAR_TRUE);
        carMapper.updateByPrimaryKeySelective(car);
    }

    @Override
    public void updateRent(RentVo rentVo) {
        this.rentMapper.updateByPrimaryKeySelective(rentVo);
    }

    @Override
    public Rent queryRentByRentId(String rentid) {
        return this.rentMapper.selectByPrimaryKey(rentid);
    }
}
