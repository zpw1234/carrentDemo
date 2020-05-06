package com.sxt.sys.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sxt.sys.constast.SysConstast;
import com.sxt.sys.domain.Menu;
import com.sxt.sys.domain.Role;
import com.sxt.sys.mapper.MenuMapper;
import com.sxt.sys.mapper.RoleMapper;
import com.sxt.sys.service.RoleService;
import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.utils.TreeNode;
import com.sxt.sys.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Role> queryAllRoleForList(RoleVo roleVo) {
        return roleMapper.queryAllRole(roleVo);
    }

    @Override
    public List<Role> queryRoleByUserIdForList(RoleVo roleVo, Integer userId) {
        return roleMapper.queryAllRole(roleVo);
    }

    @Override
    public DataGridView queryAllRole(RoleVo roleVo) {
        Page<Object> page = PageHelper.startPage(roleVo.getPage(),roleVo.getLimit());
        List<Role> data = this.roleMapper.queryAllRole(roleVo);
        return new DataGridView(page.getTotal(),data);
    }

    @Override
    public void addRole(RoleVo roleVo) {
       this.roleMapper.insertSelective(roleVo);
    }

    @Override
    public void updateRole(RoleVo roleVo) {
        this.roleMapper.updateByPrimaryKeySelective(roleVo);
    }

    @Override
    public void deleteRole(Integer roleId) {
        //删除角色表的数据
        this.roleMapper.deleteByPrimaryKey(roleId);
        //根据角色id删除sys_role_menu里面的数据
        this.roleMapper.deleteRoleMenuByRid(roleId);
        //根据角色id删除sys_role_user里面的数据
        this.roleMapper.deleteRoleUserByRid(roleId);
    }

    @Override
    public void deleteRole(Integer[] ids) {
        for (Integer rid : ids){
            deleteRole(rid);
        }
    }

    @Override
    public DataGridView initRoleMenuTreeJson(Integer roleid) {
        //查询所有的可用的菜单
        Menu menu = new Menu();
        menu.setAvailable(SysConstast.AVAILABLE_TRUE);
        List<Menu> allMenu = menuMapper.queryAllMenu(menu);
        //根据角色ID查询当前角色拥有的菜单
        List<Menu> roleMenu = menuMapper.queryMenuByRoleId(SysConstast.AVAILABLE_TRUE,roleid);

        List<TreeNode> data = new ArrayList<>();
        for (Menu m1 : allMenu) {
            String checkArr = SysConstast.CODE_ZERO+"";
            for (Menu m2 : roleMenu) {
                if (m1.getId()==m2.getId()){
                    checkArr=SysConstast.CODE_ONE+"";
                    break;
                }
            }
            Integer id = m1.getId();
            Integer pid = m1.getPid();
            String title = m1.getTitle();
            Boolean spread = m1.getSpread()==SysConstast.SPREAD_TRUE?true:false;
            data.add(new TreeNode(id,pid,title,spread,checkArr));
        }

        return new DataGridView(data);
    }

    @Override
    public void saveRoleMenu(RoleVo roleVo){
        Integer rid = roleVo.getRoleid();
        Integer[] mids = roleVo.getIds();
        this.roleMapper.deleteRoleMenuByRid(rid);
        //保存角色和菜单关系
        for (Integer mid : mids){
            this.roleMapper.insertRoleMenu(rid,mid);
        }
    }

}
