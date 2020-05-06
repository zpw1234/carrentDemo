package com.sxt.sys.service;

import com.sxt.sys.domain.News;
import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.vo.NewsVo;
import com.sxt.sys.vo.UserVo;

public interface NewsService {
    /**
     * 查询所有公告
     * @param newsVo
     * @return
     */
    public DataGridView queryAllNews(NewsVo newsVo);

    /**
     * 添加公告
     * @param newsVo
     */
    public void addNews(NewsVo newsVo);

    /**
     * 修改公告
     * @param newsVo
     */
    public void updateNews(NewsVo newsVo);

    /**
     * 根据id删除公告
     * @param newsid
     */
    public void deleteNews(Integer newsid);

    /**
     * 批量删除公告
     * @param ids
     */
    public void deleteBatchNews(Integer [] ids);

    /**
     * 通过id查询
     * @param id
     * @return
     */
    News queryNewsById(Integer id);
}
