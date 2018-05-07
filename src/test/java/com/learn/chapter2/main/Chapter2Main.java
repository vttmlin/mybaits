package com.learn.chapter2.main;

import com.learn.chapter2.mapper.RoleMapper;
import com.learn.chapter2.po.Role;
import com.learn.chapter2.util.SqlSessionFactoryUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.scripting.xmltags.OgnlClassResolver;
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
//            role = roleMapper.getRole(1L);
//            System.out.println(role.getId());
//            sqlSession.commit();
            role = roleMapper.getRoleParameter(1L,true);
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


    /**
     *  {@link OgnlClassResolver#classForName(java.lang.String, java.util.Map)}
     *  这地方出现了一个异常 原本我们可以通过提前判断 来规避这个风险
     *  java.lang.ClassNotFoundException: Cannot find class: Integer
     *      at org.apache.ibatis.io.ClassLoaderWrapper.classForName(ClassLoaderWrapper.java:200)
     *      at org.apache.ibatis.io.ClassLoaderWrapper.classForName(ClassLoaderWrapper.java:89)
     *      at org.apache.ibatis.io.Resources.classForName(Resources.java:257)
     *      at com.learn.chapter2.main.Chapter2Main.testClassForName(Chapter2Main.java:67)
     *  通过提前增加判断就可以规避这个风险
     *
     *  经过3次耗时比对 速度差了几倍
     * */
    @Test
    public void testClassForName(){
        System.err.println("测试mybatis OgnlClassResolver 性能问题");
        String s = "Integer";
        long l = System.nanoTime();
        try {
            Resources.classForName(s);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.err.println("消耗时间 "+(System.nanoTime()-l));//2795779  3274058  3656373  平均 3242070
    }

    @Test
    public void testClassForName2(){
        System.err.println("测试mybatis OgnlClassResolver 性能问题");
        String s = "Integer";
        long l = System.nanoTime();
        try {
            if(s.indexOf(".")==-1){
                s="java.lang."+s;
            }
            Resources.classForName(s);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.err.println("消耗时间 "+(System.nanoTime()-l));//1505656  1428166  1453312  平均  1462381
    }
}
