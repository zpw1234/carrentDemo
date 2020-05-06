package com.sxt.sys.service;

import com.sxt.sys.domain.Menu;
import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.vo.MenuVo;

import java.util.List;

public interface MenuService {
    /**
     * 查询所有菜单返回
     * @param menuVo
     * @return
     */
    public List<Menu> queryAllMenuForList(MenuVo menuVo);

    /**
     * 根据id查询
     */
    public List<Menu> queryMenuByUserIdForList(MenuVo menuVo,Integer userId);

    /**
     * 查询所有菜单
     */
    public DataGridView queryAllMenu(MenuVo menuVo);

    /**
     * 添加菜单
     * @param menuVo
     */
    public void addMenu(MenuVo menuVo);

    /**
     * 修改菜单
     * @param menuVo
     */
    public void updateMenu(MenuVo menuVo);

    /**
     * 判断当前菜单有没子节点
     * 有返回code>=0
     * 没有 返回code<0
     */
    public Integer queryMenuByPid(Integer pid);

    /**
     * 根据id删除菜单
     * @param menuVo
     */
    public void deleteMenu(MenuVo menuVo);
}
