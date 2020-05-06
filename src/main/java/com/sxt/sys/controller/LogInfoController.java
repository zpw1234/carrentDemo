package com.sxt.sys.controller;

import com.sxt.sys.domain.LogInfo;
import com.sxt.sys.service.LogInfoService;
import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.utils.ResultObj;
import com.sxt.sys.vo.LogInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 日志管理
 */
@RestController
@RequestMapping("logInfo")
public class LogInfoController  {
    @Autowired
    private LogInfoService logInfoService;

    /**
     * 加载日志列表返回DataGridView
     * @param logInfoVo
     * @return
     */
    @RequestMapping("loadAllLogInfo")
    public DataGridView loadAllLogInfo(LogInfoVo logInfoVo){
        return this.logInfoService.queryAllLogInfo(logInfoVo);
    }

    /**
     * 删除
     * @param logInfo
     * @return
     */
    @RequestMapping("deleteLogInfo")
    public ResultObj deleteLogInfo(LogInfo logInfo){
        try {
            this.logInfoService.deleteLogInfo(logInfo.getId());
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 批量删除
     */
    @RequestMapping("deleteBatchLogInfo")
    public ResultObj deleteBatchLogInfo(LogInfoVo logInfoVo){
        try {
            this.logInfoService.deleteBatchLogInfo(logInfoVo.getIds());
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

}
