package com.sxt.bus.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sxt.bus.domain.Car;
import com.sxt.bus.mapper.CarMapper;
import com.sxt.bus.service.CarService;
import com.sxt.bus.vo.CarVo;
import com.sxt.sys.utils.DataGridView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    @Autowired
    private CarMapper carMapper;

    /**
     * 查询所有汽车
     * @param carVo
     * @return
     */
    @Override
    public DataGridView queryAllCar(CarVo carVo) {
        Page<Object> page = PageHelper.startPage(carVo.getPage(),carVo.getLimit());
        List<Car> data = this.carMapper.queryAllCar(carVo);

        return new DataGridView(page.getTotal(),data);
    }

    @Override
    public void deleteCar(String carnumber) {
        this.carMapper.deleteByPrimaryKey(carnumber);
    }

    @Override
    public void addCar(CarVo carVo) {
        this.carMapper.insertSelective(carVo);
    }

    @Override
    public void updateCar(CarVo carVo) {
        this.carMapper.updateByPrimaryKeySelective(carVo);
    }

    @Override
    public void deleteBatchCar(String[] carnumbers) {
        for (String carnumber : carnumbers){
            deleteCar(carnumber);
        }
    }

    @Override
    public Car queryCarByCarNumber(String carnumber) {
        return this.carMapper.selectByPrimaryKey(carnumber);
    }

}
