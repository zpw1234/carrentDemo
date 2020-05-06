package com.sxt.sys.service;

import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.vo.LogInfoVo;

public interface LogInfoService {

    /**
     * 查询所有日志
     * @param logInfoVo
     * @return
     */
    public DataGridView queryAllLogInfo(LogInfoVo logInfoVo);

    /**
     * 根据id删除
     * @param id
     */
    void deleteLogInfo(Integer logInfoid);

    /**
     * 批量删除
     * @param logInfoVo
     */
    void deleteBatchLogInfo(Integer[] ids);

    /**
     * 添加日志
     * @param logInfoVo
     */
    void addLogInfo(LogInfoVo logInfoVo);
}
