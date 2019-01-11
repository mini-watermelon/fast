package com.wisdomelon.test.mapper;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.wisdomelon.base.utils.datetime.DateUtils;
import com.wisdomelon.base.utils.generate.CreateIDUtils;
import com.wisdomelon.sys.entity.ser.FtSerFestival;
import com.wisdomelon.sys.mapper.ser.FtSerFestivalMapper;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;

/**
 * @author wisdomelon
 * @date 2019/1/10 0010
 * @project fast
 * @jdk 1.8
 */
public class TestMapper {

    private ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-config.xml","spring/spring-datasource.xml");


    @Test
    public void test1() throws SQLException {
        DruidDataSource dataSource = applicationContext.getBean(DruidDataSource.class);
        DruidPooledConnection connection = dataSource.getConnection();
        System.out.println(connection);
    }

    @Test
    public void testSelectOne() {
    }

    @Test
    public void testSelect() {
    }

    @Test
    public void testSelectAll() {

    }

    @Test
    public void testSelectCount() {

    }

    @Test
    public void testSelectByPrimaryKey() {


    }

    @Test
    public void testExistsWithPrimaryKey() {

    }

    @Test
    public void testInsert() {

        FtSerFestival festival = new FtSerFestival();
        festival.setId(CreateIDUtils.getFilterUnqiueID());
        festival.setName("1");
        festival.setNowYear("2019");
        festival.setStartDate(DateUtils.getNowDateStr());
        festival.setEndDate(DateUtils.getNowDateStr());
        festival.setDayCount(1);
        festival.setRemark("hello");

        FtSerFestivalMapper festivalMapper = applicationContext.getBean(FtSerFestivalMapper.class);
        festivalMapper.insert(festival);
    }

    @Test
    public void testInsertSelective() {
    }

    @Test
    public void testUpdateByPrimaryKey() {
    }

    @Test
    public void testUpdateByPrimaryKeySelective() {

    }

    @Test
    public void testDelete() {


    }

    @Test
    public void testDeleteByPrimaryKey() {


    }

    @Test
    public void testSelectByExample() {

    }

    @Test
    public void testSelectOneByExample() {

    }

    @Test
    public void testSelectCountByExample() {

    }

    @Test
    public void testDeleteByExample() {

    }

    @Test
    public void testUpdateByExample() {

    }

    @Test
    public void testUpdateByExampleSelective() {

    }

    @Test
    public void testSelectByExampleAndRowBounds() {

    }

    @Test
    public void testSelectByRowBounds() {


    }
}
