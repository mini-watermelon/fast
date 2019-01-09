package com.wisdomelon.base.code.sql.build;

import com.wisdomelon.base.code.sql.BuildSQL;
import com.wisdomelon.base.code.sql.entity.Delete;
import com.wisdomelon.base.code.sql.entity.Insert;
import com.wisdomelon.base.code.sql.entity.Truncate;
import com.wisdomelon.base.code.sql.entity.Update;
import com.wisdomelon.base.code.sql.strategy.Strategy;
import com.wisdomelon.base.code.template.entity.Table;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author wisdomelon
 * @date 2019/1/9 0009
 * @project fast
 * @jdk 1.8
 */
public abstract class AbstractBuildSQL implements BuildSQL{

    @Override
    public String dropTable(Table table) {
        String sql = "DROP TABLE " + table.getTableName();
        return sql;
    }

    @Override
    public String createTruncate(Truncate truncate) {
        String sql = "TRUNCATE TABLE " + truncate.getTableName();
        return sql;
    }

    @Override
    public List<String> createInsert(List<Insert> insertList) {
        List<String> buildSQLInsert = new ArrayList<>();
        try {
            for (Insert insert: insertList) {
                List<String> listFieldName = insert.getListFieldName();
                Map<String, String> mapFieldValue = insert.getMapFieldValue();
                Map<String, String> mapFieldStrategy = insert.getMapFieldStrategy();

                StringBuilder fieldNames = new StringBuilder();
                StringBuilder fieldValues = new StringBuilder();
                for (int i=0; i<listFieldName.size(); i++){
                    String field = listFieldName.get(i);
                    String value = getFieldValue(mapFieldValue, mapFieldStrategy, field);

                    if (i != listFieldName.size()-1) {
                        fieldNames.append(field).append(",");
                        fieldValues.append("'").append(value).append("',");
                    } else {
                        fieldNames.append(field);
                        fieldValues.append("'").append(value).append("'");
                    }
                }
                StringBuilder sql = new StringBuilder("INSERT INTO ").append(insert.getTableName()).append("(").append(fieldNames.toString()).append(")").append(" VALUES(").append(fieldValues.toString()).append(")");
                buildSQLInsert.add(sql.toString());
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return buildSQLInsert;
    }

    @Override
    public List<String> createUpdate(List<Update> updateList) {
        List<String> buildSQLUpdate = new ArrayList<>();
        try {
            for (Update update: updateList) {
                List<String> listFieldName = update.getListFieldName();
                Map<String, String> mapFieldValue = update.getMapFieldValue();
                Map<String, String> mapFieldStrategy = update.getMapFieldStrategy();
                Map<String, String> mapFieldWhere = update.getMapFieldWhere();

                String fieldWhereStr = getFieldWhereStr(mapFieldWhere);

                StringBuilder fieldSets = new StringBuilder();
                for (int i=0; i<listFieldName.size(); i++){
                    String field = listFieldName.get(i);
                    String value = getFieldValue(mapFieldValue, mapFieldStrategy, field);

                    if (i != listFieldName.size()-1) {
                        fieldSets.append(field).append("='").append(value).append("',");
                    } else {
                        fieldSets.append(field).append("='").append(value).append("'");
                    }
                }
                StringBuilder sql = new StringBuilder("UPDATE ").append(update.getTableName()).append(" SET ").append(fieldSets.toString()).append(fieldWhereStr);
                buildSQLUpdate.add(sql.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return buildSQLUpdate;
    }

    @Override
    public List<String> createDelete(List<Delete> deleteList) {
        List<String> buildSQLDelete = new ArrayList<>();
        for (Delete delete: deleteList) {
            Map<String, String> mapFieldWhere = delete.getMapFieldWhere();
            String fieldWhereStr = getFieldWhereStr(mapFieldWhere);
            StringBuilder sql = new StringBuilder("DELETE FROM ").append(delete.getTableName()).append(fieldWhereStr);
            buildSQLDelete.add(sql.toString());
        }

        return buildSQLDelete;
    }

    private String getFieldValue(Map<String, String> mapFieldValue, Map<String, String> mapFieldStrategy, String field) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        String value = mapFieldValue.get(field);
        String strategy = mapFieldStrategy.get(field);

        if(!"".equals(strategy) && null != strategy) {
            Class clazz = Class.forName(strategy);
            Strategy gy = (Strategy) clazz.newInstance();
            value = gy.createValue();
        }
        return value;
    }

    private String getFieldWhereStr(Map<String, String> mapFieldWhere) {
        StringBuilder fieldWhere = new StringBuilder(" WHERE ");
        for(Iterator<String> iterator = mapFieldWhere.keySet().iterator(); iterator.hasNext();) {
            String key = iterator.next();
            if("".equals(key) || null == key) {
                continue;
            }
            String value = mapFieldWhere.get(key);
            fieldWhere.append(key).append("='").append(value).append("' AND ");
        }
        String fieldWhereStr = fieldWhere.toString();
        if(fieldWhere.indexOf("AND") > 0) {
            fieldWhereStr = fieldWhereStr.substring(0, fieldWhere.lastIndexOf("AND "));
        } else {
            fieldWhereStr = "";
        }
        return fieldWhereStr;
    }


}
