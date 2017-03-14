package com.github.test;

import com.github.bean.Country;
import com.github.bean.SysUser;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiedan on 2017/3/2.
 */
public class UserMappingTest {
    private Reader reader;
    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void setUp() throws Exception {
        try {
            reader = Resources.getResourceAsReader("conf.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
    }

    /**
     * 下面使用的是直接从映射配置文件中读取相应的SQL语句并执行
     *
     * 该实现方便需要为每个实体类编写一个映射文件
     * <mapping namespace="com.github.mapping.User">
     *     <select id="getByID" parameterType="int" resultType="User">
     *         SELECT user_id, user_name, nick_name, email FROM sys_user WHERE user_id = #{id}
     *     </select>
     * </mapping>
     *
     * 并注册到Mybatis配置文件中的mappers节中
     * <mappers>
     *     <mapping resource="com/github/mapping/User.xml" />
     * </mappers>
     *
     */
    @Test
    public void testGetById() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        SysUser sysUser;
        try {


            SysUser user = sqlSession.selectOne("com.github.mapping.User.getByID", 5);
            /*user.setEmail("rmqc0909@163.com");
            user.setNickName("修改的***2");
            user.setIsValid(0);



            sqlSession.update("com.github.mapping.User.udpateUser", user);
            sqlSession.commit(); //这里一定要使用commit，否则事务不会被提交，数据操作不会反映到数据表中*/

            sysUser = sqlSession1.selectOne("com.github.mapping.User.getByID", 5);
            if (sysUser == null) {
                System.out.println("the result is null.");
            }
            else {
                System.out.println(sysUser);
            }
        } finally {
            sqlSession.close();
            sqlSession1.close();
        }
    }

    @Test
    public void testInsertUser() {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            SysUser user = new SysUser();
            user.setEmail("chris.mao.zb@###.com");
            user.setNickName("Mybatis Tester1");
            user.setUserName("rmqc0101");
            user.setIsValid(1);
            user.setUserPassword("rimuqingchen0101");
            sqlSession.insert("com.github.mapping.User.insertUser", user);
            System.out.println("New Id is " + user.getUserId()); //打印出新增记录的Id值
            sqlSession.commit(); //这里一定要使用commit，否则事务不会被提交，数据操作不会反映到数据表中
        } finally {
            sqlSession.close();
        }
    }


    @Test
    public void testUpdateUser() {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {


            SysUser user = sqlSession.selectOne("com.github.mapping.User.getByID", 3);
            user.setEmail("chris.mao.zb@＃＃＃.com");
            user.setNickName("ChrisMao");
            user.setIsValid(0);



            sqlSession.update("com.github.mapping.User.udpateUser", user);
            sqlSession.commit(); //这里一定要使用commit，否则事务不会被提交，数据操作不会反映到数据表中
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testDeleteBatch() {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            List<Integer> list = new ArrayList();
            list.add(3);
            sqlSession.delete("com.github.mapping.User.deleteBatch", list);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }


    @Test
    public void testInsertBatch() {
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);

        try {

            List<Country> countries = new ArrayList<Country>();
            Country country = new Country("cn1114","cc1114");
            countries.add(country);
            Country country2 = new Country("cn2224","cc2224");
            countries.add(country2);
            Country country3 = new Country("cn3334","cc3334");
            countries.add(country3);


            int result = sqlSession.insert("com.github.mapping.User.insertList", countries);

            //Assert.assertEquals(3, result);

            /*for (Country c:
                    countries) {
                System.out.println(c.getId());
            }*/


            sqlSession.commit(); //这里一定要使用commit，否则事务不会被提交，数据操作不会反映到数据表中
        } finally {
            sqlSession.close();
        }
    }



}
