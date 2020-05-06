package com.sxt.bus.service;

import com.sxt.bus.domain.Car;
import com.sxt.bus.domain.Rent;
import com.sxt.bus.vo.CarVo;
import com.sxt.bus.vo.RentVo;
import com.sxt.sys.utils.DataGridView;

public interface RentService {
    /**
     * 查询所有
     * @param rentVo
     * @return
     */
    public DataGridView queryAllRent(RentVo rentVo);

    /**
     * 删出租
     * @param rentid
     */
    void deleteRent(String rentid);

    /**
     * 添加车辆
     * @param rentVo
     */
    void addRent(RentVo rentVo);

    /**
     * 修改用户
     * @param rentVo
     */
    void updateRent(RentVo rentVo);


    /**
     * 根据车牌号查询
     * @param rentid
     * @return
     */
    Rent queryRentByRentId(String rentid);
}
