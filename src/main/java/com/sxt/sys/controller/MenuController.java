package com.sxt.sys.controller;

import com.sun.activation.registries.MailcapParseException;
import com.sun.org.apache.regexp.internal.RE;
import com.sxt.sys.constast.SysConstast;
import com.sxt.sys.domain.Menu;
import com.sxt.sys.domain.User;
import com.sxt.sys.service.MenuService;
import com.sxt.sys.utils.*;
import com.sxt.sys.vo.MenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @RequestMapping("loadIndexLeftMenuJson")
    public List<TreeNode> loadIndexLeftMenuJson(MenuVo menuVo){
        //得到当前登录的用户信息
        User user = (User) WebUtils.getHttpSession().getAttribute("user");

        List<Menu> list = null;
        menuVo.setAvailable(SysConstast.AVAILABLE_TRUE);//只查询可以用的
        if (user.getType() == SysConstast.USER_TYPE_SUPER){
            list = this.menuService.queryAllMenuForList(menuVo);
        }else {
            list = this.menuService.queryMenuByUserIdForList(menuVo,user.getUserid());
        }

        List<TreeNode> nodes = new ArrayList<>();
        //把list里面的数据方法哦nodes中
        for (Menu menu : list){
            Integer id = menu.getId();
            Integer pid = menu.getPid();
            String title = menu.getTitle();
            String icon = menu.getIcon();
            String href = menu.getHref();
            Boolean spread = menu.getSpread()==SysConstast.SPREAD_TRUE?true:false;
            String target = menu.getTarget();
            nodes.add(new TreeNode(id,pid,title,icon,href,spread,target));
        }
        return TreeNodeBuilder.builder(nodes,1);
    }

    /**
     * 加载菜单左边的菜单树
     * @param menuVo
     * @return
     */
    @RequestMapping("loadMenuManagerLeftTreeJson")
    public DataGridView loadMenuManagerLeftTreeJson(MenuVo menuVo){
        menuVo.setAvailable(SysConstast.AVAILABLE_TRUE);//只查询可用的
        List<Menu> list = this.menuService.queryAllMenuForList(menuVo);
        List<TreeNode> nodes = new ArrayList<>();
        //把list里面的数据放到nodes
        for (Menu menu : list){
            Integer id = menu.getId();
            Integer pid = menu.getPid();
            String title = menu.getTitle();
            String icon = menu.getIcon();
            String href = menu.getHref();
            Boolean spread = menu.getSpread()==SysConstast.SPREAD_TRUE?true:false;
            String target = menu.getTarget();
            nodes.add(new TreeNode(id,pid,title,icon,href,spread,target));
        }
        return new DataGridView(nodes);
    }

    /**
     * 加载菜单列表返回DataGridView
     * @param menuVo
     * @return
     */
    @RequestMapping("loadAllMenu")
    public DataGridView loadAllMenu(MenuVo menuVo){
        return this.menuService.queryAllMenu(menuVo);
    }

    /**
     * 添加菜单
     */
    @RequestMapping("addMenu")
    public ResultObj addMenu(MenuVo menuVo){
        try {
            this.menuService.addMenu(menuVo);
            return ResultObj.ADD_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 修改菜单
     */
    @RequestMapping("updateMenu")
    public ResultObj updateMenu(MenuVo menuVo){
        try {
            this.menuService.updateMenu(menuVo);
            return ResultObj.UPDATE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 判断当前菜单有没子节点
     * 有返回code>=0
     * 没有 返回code<0
     */
    @RequestMapping("checkMenuHasChildren")
    public ResultObj checkMenuHasChildren(MenuVo menuVo){
        System.out.println(menuVo.getId());
        //根据pid查询子菜单数量
        Integer count = this.menuService.queryMenuByPid(menuVo.getId());
        if (count > 0){
            return ResultObj.STATUS_TRUE;
        }else {
            return ResultObj.STATUS_ERROR;
        }
    }

    /**
     * 删除菜单
     */
    @RequestMapping("deleteMenu")
    public ResultObj deleteMenu(MenuVo menuVo){
        try {
            this.menuService.deleteMenu(menuVo);
            return ResultObj.DELETE_SUCCESS;
        }catch (Exception e){
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

}
