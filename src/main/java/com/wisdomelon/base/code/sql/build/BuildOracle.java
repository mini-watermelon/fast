package com.wisdomelon.base.code.sql.build;

import com.wisdomelon.base.code.template.entity.Field;
import com.wisdomelon.base.code.template.entity.Table;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wisdomelon
 * @date 2019/1/9 0009
 * @project fast
 * @jdk 1.8
 */
public class BuildOracle extends AbstractBuildSQL {

    @Override
    public String checkTable(Table table) {
        String sql = "SELECT table_name FROM user_tables WHERE table_name='" + table.getTableName().toUpperCase() + "'";
        return sql;
    }

    @Override
    public List<String> createTable(Table table) {
        List<String> buildSQLCreate = new ArrayList<>();

        List<Field> fieldList = table.getFieldList();

        StringBuilder alterKeySql = new StringBuilder();
        StringBuilder sql = new StringBuilder("CREATE TABLE ").append(table.getTableName()).append("( \n");
        for(int i=0; i<fieldList.size(); i++){
            Field field = fieldList.get(i);
            String length = ("".equals(field.getLength()) || null == field.getLength()) ? "" : "("+field.getLength()+") ";

            String special = field.getSpecial();
            String[] split = special.split("\\|");
            StringBuilder specialBuilder = new StringBuilder();
            for (String str: split) {
                switch (str) {
                    case "key":
                        alterKeySql.append("ALTER TABLE ").append(field.getTableName()).append(" ADD CONSTRAINT PK_").append(field.getTableName()).append(" PRIMARY KEY (").append(field.getName()).append(")");
                        break;
                    case "unique":
                        specialBuilder.append("UNIQUE ");
                        break;
                    case "null":
                        specialBuilder.append("NULL ");
                        break;
                    case "notnull":
                        specialBuilder.append("NOT NULL ");
                        break;
                }
            }

            String defaultValue = ("".equals(field.getDefaultValue()) || null == field.getDefaultValue()) ? "" : " DEFAULT '" + field.getDefaultValue() + "'";

            sql.append(" ").append(field.getName()).append(" ").append(field.getType()).append(length).append(" ").append(specialBuilder.toString()).append(defaultValue);

            if (i != fieldList.size()-1) {
                sql.append(", \n");
            } else {
                sql.append("\n");
            }
        }
        sql.append(") ");
        buildSQLCreate.add(sql.toString());

        StringBuilder commentSql = new StringBuilder("COMMENT ON TABLE ").append(table.getTableName()).append(" IS '").append(table.getTableComment()).append("'");
        buildSQLCreate.add(commentSql.toString());

        for(int i=0; i<fieldList.size(); i++){
            Field field = fieldList.get(i);
            StringBuilder fieldComment = new StringBuilder("COMMENT ON COLUMN ").append(field.getTableName()).append(".").append(field.getName()).append(" IS '").append(field.getComment()).append("'");
            buildSQLCreate.add(fieldComment.toString());
        }

        String alterKeySqlStr = alterKeySql.toString();
        if(!"".endsWith(alterKeySqlStr) && null != alterKeySql) {
            buildSQLCreate.add(alterKeySqlStr);

        }

        return buildSQLCreate;
    }

}
