package com.learn.chapter2.main;

import com.learn.chapter2.mapper.RoleMapper;
import com.learn.chapter2.po.Role;
import com.learn.chapter2.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * @author youxiangxin
 * @Time 2018/4/26  15:46
 * @description
 */
public class Chapter2Main {
    public static void main(String[] args) {
        SqlSession sqlSession = null;
        try {
            Role role = null;
            RoleMapper roleMapper = null;
            sqlSession = SqlSessionFactoryUtil.openSqlSession();
            roleMapper = sqlSession.getMapper(RoleMapper.class);
            role = roleMapper.getRole(1L);
            System.out.println(role.getId());
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }

    @Test
    public void testMethod() {
        Method[] declaredMethods = RoleMapper.class.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println(isDefaultMethod(declaredMethod));
            System.out.println(declaredMethod.getName() + "\t" + declaredMethod.getModifiers());
        }
    }

    private boolean isDefaultMethod(Method method) {
        return (method.getModifiers()
                & (Modifier.ABSTRACT | Modifier.PUBLIC | Modifier.STATIC)) == Modifier.PUBLIC
                && method.getDeclaringClass().isInterface();
    }

}
