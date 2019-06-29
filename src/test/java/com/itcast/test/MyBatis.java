package com.itcast.test;

import com.itcast.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public class MyBatis {

    @Test
    //查询操作
    public void test1() throws IOException {

        //获得核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //获得session工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //获得session会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //执行操作 参数:namespace+id
        List<User> userList = sqlSession.selectList("userMapper.findAll");
        //打印数据
        System.out.println(userList);
        //释放资源
        sqlSession.close();

    }

    @Test
    //添加操作
    public void test2() throws IOException {

        //模拟User对像
        User user = new User();
        user.setUsername("tom");
        user.setPassword("abc");
        //获得核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //获得session工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //获得session会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //执行操作 参数:namespace+id
        sqlSession.insert("userMapper.sava", user);
        //给数据库表中添加数据,会影响库的结构,所以执行更新错做,首先要提交事务,mybatis默认事务不提交,MySQL默认事务时提交
        sqlSession.commit();
        //释放资源
        sqlSession.close();

    }

    @Test
    //修改操作
    public void test3() throws IOException {

        //模拟User对像
        User user = new User();
        user.setId(6);
        user.setUsername("shagua");
        user.setPassword("xjb");
        //获得核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //获得session工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //获得session会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //执行操作 参数:namespace+id
        int update = sqlSession.update("userMapper.update", user);
        System.out.println(update);
        //给数据库表中添加数据,会影响库的结构,所以执行更新错做,首先要提交事务,mybatis默认事务不提交,MySQL默认事务时提交
        sqlSession.commit();
        //释放资源
        sqlSession.close();

    }

    @Test
    //删除操作
    public void test4() throws IOException {

        //获得核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        //获得session工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        //获得session会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //执行操作 参数:namespace+id
        sqlSession.delete("userMapper.delete", 6);
        //给数据库表中添加数据,会影响库的结构,所以执行更新错做,首先要提交事务,mybatis默认事务不提交,MySQL默认事务时提交
        sqlSession.commit();
        //释放资源
        sqlSession.close();

    }
}
