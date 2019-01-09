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
public class BuildMySql extends AbstractBuildSQL{

    @Override
    public String checkTable(Table table) {
        String sql = "SELECT table_name FROM information_schema.TABLES WHERE table_name='" + table.getTableName() + "'";
        return sql;
    }

    @Override
    public List<String> createTable(Table table) {
        List<String> buildSQLCreate = new ArrayList<>();

        List<Field> fieldList = table.getFieldList();
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
                        specialBuilder.append("PRIMARY KEY ");
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
            String comment = ("".equals(field.getComment()) || null == field.getComment()) ? "" : " COMMENT '" + field.getComment() + "'";

            sql.append(" ").append(field.getName()).append(" ").append(field.getType()).append(length).append(" ").append(specialBuilder.toString()).append(defaultValue).append(comment);

            if (i != fieldList.size()-1) {
                sql.append(", \n");
            } else {
                sql.append("\n");
            }
        }
        String tableComment = ("".equals(table.getTableComment()) || null == table.getTableComment()) ? "" : " COMMENT '" + table.getTableComment() + "'";
        sql.append(") ").append(tableComment);
        buildSQLCreate.add(sql.toString());
        return buildSQLCreate;
    }


}
