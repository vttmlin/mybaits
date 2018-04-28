package org.apache.ibatis.session;

import org.junit.Test;

/**
 * @author youxiangxin
 * @Time 2018/4/27  13:59
 * @description
 */
public class TestSqlSessionFactoryBuilder {
    @Test
    public void test() {
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(this.getClass().getResourceAsStream("/mybatis-config.xml"));
        SqlSession sqlSession = sqlSessionFactory.openSession();

    }
}
