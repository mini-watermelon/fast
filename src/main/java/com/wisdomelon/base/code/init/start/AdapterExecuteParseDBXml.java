package com.wisdomelon.base.code.init.start;

import com.wisdomelon.base.code.init.abst.AbstractExecute;
import com.wisdomelon.base.code.init.exec.ExecuteSQL;
import com.wisdomelon.base.code.init.exec.ExecuteTemplate;
import com.wisdomelon.base.code.template.entity.Module;
import com.wisdomelon.base.code.xml.ParseDBXml;
import com.wisdomelon.base.code.xml.parse.MySqlParseDBXml;
import com.wisdomelon.base.code.xml.parse.OracleParseDBXml;
import org.apache.log4j.Logger;

import java.net.URL;
import java.util.List;

/**
 * @author wisdomelon
 * @date 2019/1/8 0008
 * @project fast
 * @jdk 1.8
 */
public class AdapterExecuteParseDBXml extends AbstractExecute {

    private static final Logger LOGGER = Logger.getLogger(AdapterExecuteParseDBXml.class);

    private AdapterExecuteParseDBXml(){}

    private static class AdapterExecuteParseDBXmlHolder {
        private static final AdapterExecuteParseDBXml INSTANCE = new AdapterExecuteParseDBXml();
    }

    public static final AdapterExecuteParseDBXml getInstance() {
        return AdapterExecuteParseDBXmlHolder.INSTANCE;
    }

    public static final AdapterExecuteParseDBXml getInstance(String dbXmlDir) {
        return AdapterExecuteParseDBXml.getInstance(dbXmlDir, null, null, null, null, null, null);
    }

    public static final AdapterExecuteParseDBXml getInstance(String dbXmlDir, String templateDir) {
        return AdapterExecuteParseDBXml.getInstance(dbXmlDir, templateDir, null, null, null, null, null);
    }

    public static final AdapterExecuteParseDBXml getInstance(String dbName, String driverClass, String jdbcUrl, String username, String password) {
        return AdapterExecuteParseDBXml.getInstance(null, null, dbName, driverClass, jdbcUrl, username, password);
    }

    public static final AdapterExecuteParseDBXml getInstance(String dbXmlDir, String templateDir, String dbName, String driverClass, String jdbcUrl, String username, String password) {
        AdapterExecuteParseDBXml instance = AdapterExecuteParseDBXmlHolder.INSTANCE;
        instance.setDbXmlDir(dbXmlDir);
        instance.setTemplateDir(templateDir);
        instance.setDbName(dbName);
        instance.setDriverClass(driverClass);
        instance.setJdbcUrl(jdbcUrl);
        instance.setUsername(username);
        instance.setPassword(password);
        return instance;
    }

    @Override
    public void execute() {
        //1. 设置dbxml文件的路径
        if("".equals(dbXmlDir) || null == dbXmlDir) {
            // 说明没有配置这个路径，则尝试从默认classpath中去取
            URL resource = Thread.currentThread().getContextClassLoader().getResource("dbxml/");
            if(resource == null) {
                // 说明路径不存在
                LOGGER.info("未配置xml文件路径，且默认路径不存在，本次解析结束。");
                return;
            } else {
                dbXmlDir = resource.getPath();
            }
        }

        //2. 判断数据库类型，执行相应的解析
        if ("".equals(dbName) || null == dbName) {
            if("".equals(jdbcUrl) || null == jdbcUrl) {
                LOGGER.info("未配置数据库信息，本次解析结束。");
                return;
            } else {
                if(jdbcUrl.indexOf("mysql") > -1) {
                    dbName = "mysql";
                } else if(jdbcUrl.indexOf("oracle") > -1) {
                    dbName = "oracle";
                }
            }
        }

        ParseDBXml parseDBXml = null;
        switch (dbName) {
            case MYSQL:
                dbXmlDir += MYSQL + "/";
                parseDBXml = new MySqlParseDBXml();
                break;
            case ORACLE:
                dbXmlDir += ORACLE + "/";
                parseDBXml = new OracleParseDBXml();
            break;
        }
        List<Module> moduleList =  parseDBXml.parse(dbXmlDir);

        if(moduleList.size() > 0) {
            //3. 根据moduleList数据,执行建表建SQL操作
            ExecuteSQL.getInstance(moduleList, dbName, driverClass, jdbcUrl, username, password).execute();

            //4. 根据moduleList数据,执行生产模板操作
            ExecuteTemplate.getInstance(moduleList, templateDir).execute();
        }
    }

}
