package com.sxt.sys.mapper;

import com.sxt.sys.domain.Role;
import com.sxt.sys.domain.RoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoleMapper {
    long countByExample(RoleExample example);

    int deleteByExample(RoleExample example);

    int deleteByPrimaryKey(Integer roleid);

    int insert(Role record);

    int insertSelective(Role record);

    List<Role> selectByExample(RoleExample example);

    Role selectByPrimaryKey(Integer roleid);

    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByExample(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    /**
     * 查询角色
     */
    List<Role> queryAllRole(Role role);

    /**
     * 根据角色id删除sys_role_menu里面的数据
     * @param roleId
     */
    void deleteRoleMenuByRid(Integer roleId);

    /**
     * 根据角色id删除sys_role_user里面的数据
     * @param roleId
     */
    void deleteRoleUserByRid(Integer roleId);

    /**
     * 保存角色和菜单的关系
     * @param rid
     * @param mid
     */
    void insertRoleMenu(@Param("rid") Integer rid,@Param("mid") Integer mid);

    /**
     * 根据用户id删除sys_role_user里面的数据
     */
    void deleteRoleUserByUid(Integer userid);

    /**
     * 根据用户ID查询角色
     * @param available
     * @param userid
     * @return
     */
    List<Role> queryRoleByUid(@Param("available") Integer available,@Param("uid") Integer userid);
}