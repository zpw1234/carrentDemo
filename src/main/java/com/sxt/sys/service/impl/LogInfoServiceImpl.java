package com.sxt.sys.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sxt.sys.domain.LogInfo;
import com.sxt.sys.mapper.LogInfoMapper;
import com.sxt.sys.service.LogInfoService;
import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.vo.LogInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogInfoServiceImpl implements LogInfoService {
    @Autowired
    private LogInfoMapper logInfoMapper;


    /**
     * 查询日志
     * @param logInfoVo
     * @return
     */
    @Override
    public DataGridView queryAllLogInfo(LogInfoVo logInfoVo) {
        Page<Object> page= PageHelper.startPage(logInfoVo.getPage(),logInfoVo.getLimit());
        List<LogInfo> data = this.logInfoMapper.queryAllLogInfo(logInfoVo);
        return new DataGridView(page.getTotal(),data);
    }

    /**
     * 根据id删除
     */
    public void deleteLogInfo(Integer logInfoid){
        this.logInfoMapper.deleteByPrimaryKey(logInfoid);
    }

    /**
     * 批量删除
     * @param logInfoVo
     */
    public void deleteBatchLogInfo(Integer[] ids){
        for (Integer id : ids){
            deleteLogInfo(id);
        }
    }

    /**
     * 添加日志
     * @param logInfoVo
     */
    @Override
    public void addLogInfo(LogInfoVo logInfoVo) {
        this.logInfoMapper.insertSelective(logInfoVo);
    }
}
