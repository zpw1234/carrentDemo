package com.sxt.sys.mapper;

import com.sxt.sys.domain.Menu;
import com.sxt.sys.domain.MenuExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MenuMapper {
    long countByExample(MenuExample example);

    int deleteByExample(MenuExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Menu record);

    int insertSelective(Menu record);

    List<Menu> selectByExample(MenuExample example);

    Menu selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Menu record, @Param("example") MenuExample example);

    int updateByExample(@Param("record") Menu record, @Param("example") MenuExample example);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);

    /**
     * 查询所有
     */
    List<Menu> queryAllMenu(Menu menu);

    /**
     *根据用户id查询表单
     */
    List<Menu> queryMenuByUid(@Param("available") Integer available,@Param("uid") Integer userId);

    /**
     * 根据pid查询菜单数量
     * @param pid
     * @return
     */
    Integer queryMenuByPid(@Param("pid") Integer pid);

    /**
     * 根据菜单id删除sys_role_menu里面的数据
     * @param mid
     */
    void deleteRoleMenuByMid(@Param("mid") Integer mid);

    /**
     * 根据角色id查询菜单
     * @param available
     * @param rid
     * @return
     */
    List<Menu> queryMenuByRoleId(@Param("available") Integer available,@Param("rid") Integer roleid);
}