package com.sxt.bus.service;

import com.sxt.bus.domain.Car;
import com.sxt.bus.domain.Check;
import com.sxt.bus.vo.CarVo;
import com.sxt.bus.vo.CheckVo;
import com.sxt.sys.utils.DataGridView;

import java.util.Map;

public interface CheckService {
    /**
     * 根据出租单号加载检测单的表单数据
     * @param rentid
     * @return
     */
    Map<String,Object> initCheckFormData(String rentid);

    /**
     * 查询所有
     * @param checkVo
     * @return
     */
    public DataGridView queryAllCheck(CheckVo checkVo);

    /**
     * 删除检查单
     * @param checkVo
     */
    void deleteCheck(CheckVo checkVo);

    /**
     * 添加车辆
     * @param checkVo
     */
    void addCheck(CheckVo checkVo);

    /**
     * 修改用户
     * @param checkVo
     */
    void updateCheck(CheckVo checkVo);

    /**
     * 批量删除
     * @param
     */
    void deleteBatchCheck(String[] ids);

}
