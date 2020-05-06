package com.sxt.sys.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sxt.sys.domain.News;
import com.sxt.sys.mapper.NewsMapper;
import com.sxt.sys.service.NewsService;
import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.vo.NewsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsMapper newsMapper;

    @Override
    public DataGridView queryAllNews(NewsVo newsVo) {
        Page<Object> page = PageHelper.startPage(newsVo.getPage(),newsVo.getLimit());
        List<News> news = this.newsMapper.queryAllNews(newsVo);
        return new DataGridView(page.getTotal(),news);
    }

    @Override
    public void addNews(NewsVo newsVo) {
        this.newsMapper.insertSelective(newsVo);
    }

    @Override
    public void updateNews(NewsVo newsVo) {
        this.newsMapper.updateByPrimaryKeySelective(newsVo);
    }

    @Override
    public void deleteNews(Integer newsid) {
        this.newsMapper.deleteByPrimaryKey(newsid);
    }

    @Override
    public void deleteBatchNews(Integer[] ids) {
        for (Integer id : ids){
            deleteNews(id);
        }
    }

    @Override
    public News queryNewsById(Integer id) {
       return this.newsMapper.selectByPrimaryKey(id);
    }
}
