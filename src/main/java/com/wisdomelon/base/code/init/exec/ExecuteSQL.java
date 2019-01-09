package com.wisdomelon.base.code.init.exec;

import com.wisdomelon.base.code.init.abst.AbstractExecute;
import com.wisdomelon.base.code.sql.BuildSQL;
import com.wisdomelon.base.code.sql.build.BuildMySql;
import com.wisdomelon.base.code.sql.build.BuildOracle;
import com.wisdomelon.base.code.sql.entity.Operate;
import com.wisdomelon.base.code.template.entity.Module;
import com.wisdomelon.base.code.template.entity.Table;
import com.wisdomelon.base.code.utils.DBUtils;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;

/**
 * @author wisdomelon
 * @date 2019/1/9 0009
 * @project fast
 * @jdk 1.8
 */
public class ExecuteSQL extends AbstractExecute{

    private static final Logger LOGGER = Logger.getLogger(ExecuteSQL.class);

    private List<Module> moduleList;

    private ExecuteSQL(){}

    private static class ExecuteSQLHolder {
        private static final ExecuteSQL INSTANCE = new ExecuteSQL();
    }

    public static final ExecuteSQL getInstance() {
        return ExecuteSQLHolder.INSTANCE;
    }

    public static final ExecuteSQL getInstance(List<Module> moduleList, String dbName, String driverClass, String jdbcUrl, String username, String password) {
        ExecuteSQL instance = ExecuteSQLHolder.INSTANCE;
        instance.setModuleList(moduleList);
        instance.setDbName(dbName);
        instance.setDriverClass(driverClass);
        instance.setJdbcUrl(jdbcUrl);
        instance.setUsername(username);
        instance.setPassword(password);
        return instance;
    }

    public List<Module> getModuleList() {
        return moduleList;
    }

    public void setModuleList(List<Module> moduleList) {
        this.moduleList = moduleList;
    }

    @Override
    public void execute() {

        try {
            // 1. 获取buildSQL对象
            BuildSQL buildSQL = null;
            switch (dbName) {
                case MYSQL:
                    buildSQL = new BuildMySql();
                    break;
                case ORACLE:
                    buildSQL = new BuildOracle();
                    break;
            }

            // 2.初始化DBUtils
            DBUtils.init(driverClass, jdbcUrl, username, password);

            // 3.遍历moduleList，开始生成语句
            for (Module module: moduleList) {
                List<Table> tableList = module.getTableList();
                for (Table table: tableList) {
                    String tableType = table.getTableType();
                    // 创建类型，create-无则create，有则跳过；modify-无则create，有则先drop后create；skip-无论有无直接skip
                    if("skip".equals(tableType)) { //skip
                        continue;
                    } else if ("modify".equals(tableType)) { //modify
                        if(check(buildSQL, table)) {
                            flow(buildSQL, table);
                        } else {
                            drop(buildSQL, table);
                            flow(buildSQL, table);
                        }
                    } else { //create或默认
                        if(check(buildSQL, table)) {
                            flow(buildSQL, table);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean check(BuildSQL buildSQL, Table table) throws Exception {
        String checkTable = buildSQL.checkTable(table);
        LOGGER.info("SQL: " + checkTable);
        List<Map<String, Object>> maps = DBUtils.execQuery(checkTable);
        return maps.size() == 0;
    }

    private void drop(BuildSQL buildSQL, Table table) throws Exception {
        String dropTable = buildSQL.dropTable(table);
        LOGGER.info("SQL: " + dropTable);
        DBUtils.execUpdate(dropTable);
    }

    private void flow(BuildSQL buildSQL, Table table) throws Exception {
        execUpdate(buildSQL.createTable(table));
        Operate operate = table.getOperate();
        if(operate.getTruncate() != null) {
            String buildSQLTruncate = buildSQL.createTruncate(operate.getTruncate());
            LOGGER.info("SQL: " + buildSQLTruncate);
            DBUtils.execUpdate(buildSQLTruncate);
        }
        if(operate.getInsertList().size() > 0) {
            execUpdate(buildSQL.createInsert(operate.getInsertList()));
        }
        if(operate.getUpdateList().size() > 0) {
            execUpdate(buildSQL.createUpdate(operate.getUpdateList()));
        }
        if(operate.getDeleteList().size() > 0) {
            execUpdate(buildSQL.createDelete(operate.getDeleteList()));
        }
    }

    private void execUpdate(List<String> buildSQL) throws Exception {
        for (String sql : buildSQL) {
            LOGGER.info("SQL: " + sql);
            DBUtils.execUpdate(sql);
        }
    }
}
