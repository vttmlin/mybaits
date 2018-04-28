package com.learn.chapter2.mapper;

import com.learn.chapter2.po.Role;

/**
 * @author youxiangxin
 * @Time 2018/4/26  14:57
 * @description
 */
public interface RoleMapper {
    Role getRole(Long id);

    int deleteRole(Long id);

    int insertRole(Role role);
}
