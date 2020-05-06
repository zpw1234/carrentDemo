package com.sxt.bus.controller;

import com.sxt.bus.domain.Rent;
import com.sxt.bus.service.CheckService;
import com.sxt.bus.service.RentService;
import com.sxt.bus.vo.CheckVo;
import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.utils.ResultObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@RestController
@RequestMapping("check")
public class CheckController {
    @Autowired
    private CheckService checkService;

    @Autowired
    private RentService rentService;

    /**
     * 根据出租单号加载检查单的表单数据
     * @param rentid
     * @return
     */
    @RequestMapping("initCheckFormData")
    public Map<String,Object> initCheckFormData(String rentid){
        return this.checkService.initCheckFormData(rentid);
    }

    /**
     * 根据出租单到查询出租信息
     */
    @RequestMapping("checkRentExist")
    public Rent checkRentExist(String rentid){
        Rent rent = this.rentService.queryRentByRentId(rentid);
        return rent;
    }

    /**
     * 保存检查单数据
     * @param checkVo
     * @return
     */
    @RequestMapping("saveCheck")
    public ResultObj saveCheck(CheckVo checkVo){
        try{
            checkVo.setCreatetime(new Date());
            this.checkService.addCheck(checkVo);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 查询所有检查单
     * @param checkVo
     * @return
     */
    @RequestMapping("loadAllCheck")
    public DataGridView loadAllCheck(CheckVo checkVo){
        return this.checkService.queryAllCheck(checkVo);
    }

    /**
     * 批量删除检查单
     */
    @RequestMapping("deleteBatchCheck")
    public ResultObj deleteBatchCheck(CheckVo checkVo){
        try {
            this.checkService.deleteBatchCheck(checkVo.getIds());
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 删除单个检查单
     */
    @RequestMapping("deleteCheck")
    public ResultObj deleteCheck(CheckVo checkVo){
        try {
            this.checkService.deleteCheck(checkVo);
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 修改检查单
     */
    @RequestMapping("updateCheck")
    public ResultObj updateCheck(CheckVo checkVo){
        try {
            this.checkService.updateCheck(checkVo);
            return ResultObj.UPDATE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }
}
