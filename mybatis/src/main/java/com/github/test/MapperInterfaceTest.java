package com.github.test;

import com.github.bean.Country;
import com.github.mapper.CountryMapper;
import com.github.mapper.ICommunicatorMapper;
import com.github.bean.Communicator;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiedan on 2017/3/2.
 */
public class MapperInterfaceTest {

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
     * 下面使用了接口和注解的方式来执行同样的代码 定义一个操作数据实体表的接口，并使用注解方式把SQL查询语句与接口方法进行绑定 public
     * interface ICommunicatorMapper { @Select(
     * "SELECT * FROM communicator WHERE communicator_id=#{id}") public
     * Communicator getById(@Param(value = "id") int id); }
     *
     * 然后将接口注册到Mybatis的配置文件中即可
     * <mappers>
     *     <mapping class="com.github.mapper.ICommunicatorMapper" />
     * </mappers>
     *
     * 当数据表很多的时候，需要写很多的映射关系，这样也比较麻烦，Mybatis3.2.1提供了 一种更加简洁的方法，可以一次指定多个映射接口
     * <mappers> <package name="com.github.mapper" /> </mappers>
     *
     */
    @Test
    public void testGetById() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            ICommunicatorMapper iCommunicatorDao = sqlSession.getMapper(ICommunicatorMapper.class);
            Communicator communicator = iCommunicatorDao.getById(1);
            if (communicator == null) {
                System.out.println("the result is null.");
            }
            else {
                System.out.println(communicator);
            }
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testInsertBatch() {
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.SIMPLE, false);
        try {
            CountryMapper countryMapper = sqlSession.getMapper(CountryMapper.class);
            List<Country> countries = new ArrayList();
            Country country = new Country(null,"cn988999","cc1");
            countries.add(country);
            Country country2 = new Country(null,"cn988999","cc2");
            countries.add(country2);
            Country country3 = new Country(null,"cn988999","cc3");
            countries.add(country3);
            int result = countryMapper.insertList(countries);
            sqlSession.commit();
            for (Country c:
                 countries) {
                System.out.println(c.getId());
            }
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testGetCountryById() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            CountryMapper countryMapper = sqlSession.getMapper(CountryMapper.class);
            Country country = countryMapper.getCountryById(184);
            System.out.println(country);
        } finally {
            sqlSession.close();
        }
    }

}
