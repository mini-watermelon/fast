package com.wisdomelon.base.code.sql;

import com.wisdomelon.base.code.sql.entity.Delete;
import com.wisdomelon.base.code.sql.entity.Insert;
import com.wisdomelon.base.code.sql.entity.Truncate;
import com.wisdomelon.base.code.sql.entity.Update;
import com.wisdomelon.base.code.template.entity.Table;

import java.util.List;

/**
 * @author wisdomelon
 * @date 2019/1/9 0009
 * @project fast
 * @jdk 1.8
 */
public interface BuildSQL {

    String checkTable(Table table);

    String dropTable(Table table);

    List<String> createTable(Table table);

    String createTruncate(Truncate truncate);

    List<String> createInsert(List<Insert> insertList);

    List<String> createUpdate(List<Update> updateList);

    List<String> createDelete(List<Delete> deleteList);
}
