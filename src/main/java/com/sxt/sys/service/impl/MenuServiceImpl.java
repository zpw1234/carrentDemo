package com.sxt.sys.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sxt.sys.domain.Menu;
import com.sxt.sys.mapper.MenuMapper;
import com.sxt.sys.service.MenuService;
import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.vo.MenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuMapper menuMapper;

    /**
     * 查询所有菜单返回
     */
    @Override
    public List<Menu> queryAllMenuForList(MenuVo menuVo){
        return menuMapper.queryAllMenu(menuVo);
    }

    /**
     * 通过id查询
     */
    @Override
    public List<Menu> queryMenuByUserIdForList(MenuVo menuVo,Integer userId){
        return menuMapper.queryMenuByUid(menuVo.getAvailable(),userId);
    }

    /**
     * 查询所有菜单
     */
    public DataGridView queryAllMenu(MenuVo menuVo){
        Page<Object> page = PageHelper.startPage(menuVo.getPage(),menuVo.getLimit());
        List<Menu> data = this.menuMapper.queryAllMenu(menuVo);

        return new DataGridView(page.getTotal(),data);
    }

    /**
     * 添加菜单
     * @param menuVo
     */
    @Override
    public void addMenu(MenuVo menuVo){
        this.menuMapper.insertSelective(menuVo);
    }

    /**
     * 修改菜单
     * @param menuVo
     */
    @Override
    public void updateMenu(MenuVo menuVo){
        this.menuMapper.updateByPrimaryKeySelective(menuVo);
    }

    /**
     * 判断当前菜单有没子节点
     * 有返回code>=0
     * 没有 返回code<0
     */
    public Integer queryMenuByPid(Integer pid){
        return this.menuMapper.queryMenuByPid(pid);
    }

    @Override
    public void deleteMenu(MenuVo menuVo) {
        //删除菜单表数据
        this.menuMapper.deleteByPrimaryKey(menuVo.getId());

        //根据菜单id删除sys_role_menu里面的数据
        this.menuMapper.deleteRoleMenuByMid(menuVo.getId());
    }
}
