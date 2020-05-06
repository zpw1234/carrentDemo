package com.sxt.bus.controller;

import com.sxt.bus.domain.Car;
import com.sxt.bus.service.CarService;
import com.sxt.bus.vo.CarVo;
import com.sxt.bus.vo.CustomerVo;
import com.sxt.sys.constast.SysConstast;
import com.sxt.sys.utils.AppFileUtils;
import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.utils.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("car")
public class CarController {
    @Autowired
    private CarService carService;

    /**
     * 查询车辆
     * @param customerVo
     * @return
     */
    @RequestMapping("loadAllCar")
    public DataGridView loadAllCar(CarVo carVo){
        return this.carService.queryAllCar(carVo);
    }

    /**
     * 添加车辆
     */
    @RequestMapping("addCar")
    public ResultObj addCar(CarVo carVo){
        try {
            carVo.setCreatetime(new Date());
            //如果不是默认图片就去掉图片的_temp的后缀
            if(!carVo.getCarimg().equals(SysConstast.DEFAULT_CAR_IMG)){
                String filePath = AppFileUtils.updateFileName(carVo.getCarimg(), SysConstast.FILE_UPLOAD_TEMP);
                carVo.setCarimg(filePath);
            }
            this.carService.addCar(carVo);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 修改用户
     */
    @RequestMapping("updateCar")
    public ResultObj updateCar(CarVo carVo){
        try{
            String carimg = carVo.getCarimg();
            if (carimg.endsWith(SysConstast.FILE_UPLOAD_TEMP)) {
                String filePath =AppFileUtils.updateFileName(carVo.getCarimg(), SysConstast.FILE_UPLOAD_TEMP);
                carVo.setCarimg(filePath);
                //把原来的删除
                Car car = this.carService.queryCarByCarNumber(carVo.getCarnumber());
                AppFileUtils.removeFileByPath(car.getCarimg());

            }
            this.carService.updateCar(carVo);
            return ResultObj.UPDATE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 删除车辆
     */
    @RequestMapping("deleteCar")
    public ResultObj deleteCar(CarVo carVo){
        try {
            this.carService.deleteCar(carVo.getCarnumber());
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 批量删除
     */
    @RequestMapping("deleteBatchCar")
    public ResultObj deleteBatchCar(CarVo carVo){
        try {
            this.carService.deleteBatchCar(carVo.getIds());
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
}
