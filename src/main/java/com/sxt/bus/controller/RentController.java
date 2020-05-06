package com.sxt.bus.controller;

import com.sxt.bus.domain.Customer;
import com.sxt.bus.service.CustomerService;
import com.sxt.bus.service.RentService;
import com.sxt.bus.vo.RentVo;
import com.sxt.sys.constast.SysConstast;
import com.sxt.sys.domain.User;
import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.utils.RandomUtils;
import com.sxt.sys.utils.ResultObj;
import com.sxt.sys.utils.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.bind.Element;
import java.util.Date;

@RestController
@RequestMapping("rent")
public class RentController {
    @Autowired
    private RentService rentService;

    @Autowired
    private CustomerService customerService;

    /**
     * 检查身份证号是否存在
     */
    @RequestMapping("checkCustomerExist")
    public ResultObj checkCustomerExist(RentVo rentVo){
        Customer customer = customerService.queryCustomerByIdentity(rentVo.getIdentity());
        if (customer != null){
            return ResultObj.STATUS_TRUE;
        }else {
            return ResultObj.STATUS_ERROR;
        }
    }

    /**
     * 初始化添加出租单的表单的数据
     * @param rentVo
     * @return
     */
    @RequestMapping("initRentFrom")
    public RentVo initRentFrom(RentVo rentVo){
        //生成出租单号
        rentVo.setRentid(RandomUtils.createRandomStringUseTime(SysConstast.CAR_ORDER_CZ));
        //设置起租时间
        rentVo.setBegindate(new Date());
        //设置操作员
        User user =(User) WebUtils.getHttpSession().getAttribute("user");
        rentVo.setOpername(user.getRealname());
        return rentVo;
    }

    /**
     * 保存出租单信息
     * @param rentVo
     * @return
     */
    @RequestMapping("saveRent")
    public ResultObj saveRent(RentVo rentVo){
        try {
            rentVo.setCreatetime(new Date());
            //设置归还状态
            rentVo.setRentflag(SysConstast.RENT_BACK_FALSE);
            this.rentService.addRent(rentVo);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 删除
     */
    @RequestMapping("deleteRent")
    public ResultObj deleteRent(RentVo rentVo){
        try {
            this.rentService.deleteRent(rentVo.getRentid());
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 修改出租单信息
     */
    @RequestMapping("updateRent")
    public ResultObj updateRent(RentVo rentVo){
        try {
            //修改
            this.rentService.updateRent(rentVo);
            return ResultObj.UPDATE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 查询
     */
    @RequestMapping("loadAllRent")
    public DataGridView loadAllRent(RentVo rentVo){
       return this.rentService.queryAllRent(rentVo);
    }
}
