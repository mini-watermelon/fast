package com.wisdomelon.test.dom;

import com.wisdomelon.base.code.template.entity.Field;
import com.wisdomelon.base.code.template.entity.Table;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.io.StringWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wisdomelon
 * @date 2019/1/9 0009
 * @project fast
 * @jdk 1.8
 */
public class TestVelocity {

    @Test
    public void test1(){
        try {
            /* 首先，初始化运行时引擎，使用默认的配置 */

            List<Field> list = new ArrayList<>();
            Field field = new Field();
            field.setName("id");
            field.setType("varchar");
            field.setLength("50");
            field.setSpecial("primary key");
            field.setComment("1");
            list.add(field);
            Field field2 = new Field();
            field2.setName("name");
            field2.setType("varchar");
            field2.setLength("50");
            field2.setSpecial("not null");
            field2.setComment("2");
            list.add(field2);
            Field field3 = new Field();
            field3.setName("name3");
            field3.setType("varchar");
            field3.setLength("50");
            field3.setSpecial("not null");
            field3.setComment("2");
            list.add(field3);

            Table table = new Table();
            table.setTableName("s_config");
            table.setTableComment("sss");
            table.setFieldList(list);

            Velocity.init();

            /* 创建Context对象，然后把数据放进去 */

            VelocityContext context = new VelocityContext();

            context.put("table", table);

            /* 渲染模板 */

            StringWriter w = new StringWriter();
            URL resource = Thread.currentThread().getContextClassLoader().getResource("template/mysql/person.vm");
            String path = resource.getPath();
            System.out.println(path);
            // 通过一个FileReader读取模板文件
            Reader reader = new FileReader(new File(path));
            // 创建一个字符串输出流，模板输出的目标
            // 根据模板上下文对模板求值，logMsgName字符串为发生异常时候记录模板异常提供方便
            Velocity.evaluate(context, w, "logMsgName", reader);
            System.out.println(" string : " + w );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
