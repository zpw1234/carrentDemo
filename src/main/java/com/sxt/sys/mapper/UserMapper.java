package com.sxt.sys.mapper;

import com.sxt.sys.domain.User;
import com.sxt.sys.domain.UserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Integer userid);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Integer userid);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 登陆
     */
    User login(User user);

    /**
     * 查询用户
     */
    List<User> queryAllUser(User user);

    /**
     * 保存用户和角色关系
     * @param userid
     * @param rid
     */
    void insertUserRole(@Param("uid") Integer userid,@Param("rid") Integer rid);
}