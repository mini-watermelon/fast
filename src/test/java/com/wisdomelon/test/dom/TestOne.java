package com.wisdomelon.test.dom;

import com.wisdomelon.base.code.init.abst.AbstractExecute;
import com.wisdomelon.base.code.init.start.AdapterExecuteParseDBXml;
import com.wisdomelon.base.code.sql.strategy.Strategy;
import com.wisdomelon.base.code.sql.strategy.impl.UuidStrategy;
import com.wisdomelon.base.code.utils.FileUtils;
import org.junit.Test;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wisdomelon
 * @date 2019/1/8 0008
 * @project fast
 * @jdk 1.8
 */
public class TestOne {

    @Test
    public void testStrategy(){
        Strategy strategy = new UuidStrategy();
        strategy.createValue();
    }

    @Test
    public void test2(){
        URL resource = Thread.currentThread().getContextClassLoader().getResource("dbxml/");
        String path = resource.getPath();
        System.out.println(path);
        path += "mysql/xml/";
        List<File> fileList = FileUtils.getFileList(path, new ArrayList<>());
        System.out.println(fileList);
    }

    @Test
    public void test3() {
        AbstractExecute parseDBXml = AdapterExecuteParseDBXml.getInstance("mysql", "com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/fastone", "root", "asd6170864");

        parseDBXml.execute();
    }

    @Test
    public void test4() {
        AbstractExecute parseDBXml = AdapterExecuteParseDBXml.getInstance("oracle", "oracle.jdbc.driver.OracleDriver", "jdbc.url=jdbc:oracle:thin:@localhost:1521:orcl", "fast", "111111");

        parseDBXml.execute();
    }

}
