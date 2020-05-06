package com.sxt.bus.service;

import com.sxt.bus.domain.Car;
import com.sxt.bus.vo.CarVo;
import com.sxt.bus.vo.CustomerVo;
import com.sxt.sys.utils.DataGridView;

public interface CarService {
    /**
     * 查询所有
     * @param carVo
     * @return
     */
    public DataGridView queryAllCar(CarVo carVo);

    /**
     * 删除车辆
     * @param carnumber
     */
    void deleteCar(String carnumber);

    /**
     * 添加车辆
     * @param carVo
     */
    void addCar(CarVo carVo);

    /**
     * 修改用户
     * @param carVo
     */
    void updateCar(CarVo carVo);

    /**
     * 批量删除
     * @param
     */
    void deleteBatchCar(String[] carnumbers);

    /**
     * 根据车牌号查询
     * @param carnumber
     * @return
     */
    Car queryCarByCarNumber(String carnumber);
}
