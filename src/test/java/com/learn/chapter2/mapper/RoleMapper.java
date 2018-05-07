package com.learn.chapter2.mapper;

import com.learn.chapter2.po.Role;
import org.apache.ibatis.annotations.Param;

/**
 * @author youxiangxin
 * @Time 2018/4/26  14:57
 * @description
 */
public interface RoleMapper {
    Role getRole(Long id);

    Role getRoleParameter(@Param("id") Long id,@Param("flag") boolean flag);

    int deleteRole(Long id);

    int insertRole(Role role);

}
