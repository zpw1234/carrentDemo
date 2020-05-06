package com.sxt.sys.service;

import com.sxt.sys.domain.Menu;
import com.sxt.sys.domain.Role;
import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.vo.MenuVo;
import com.sxt.sys.vo.RoleVo;

import java.util.List;

public interface RoleService {
    /**
     * 查询所有菜单返回
     * @param roleVo
     * @return
     */
    public List<Role> queryAllRoleForList(RoleVo roleVo);

    /**
     * 根据id查询
     */
    public List<Role> queryRoleByUserIdForList(RoleVo roleVo, Integer userId);

    /**
     * 查询所有菜单
     */
    public DataGridView queryAllRole(RoleVo roleVo);

    /**
     * 添加菜单
     * @param roleVo
     */
    public void addRole(RoleVo roleVo);

    /**
     * 修改菜单
     * @param roleVo
     */
    public void updateRole(RoleVo roleVo);


    /**
     * 根据id删除菜单
     * @param roleId
     */
    public void deleteRole(Integer roleId);

    /**
     * 批量删除角色
     */
    public void deleteRole(Integer[] ids);

    /**
     * 加载角色管理分配菜单的JSON
     */
    public DataGridView initRoleMenuTreeJson(Integer roleid);

    public void saveRoleMenu(RoleVo roleVo);


}
