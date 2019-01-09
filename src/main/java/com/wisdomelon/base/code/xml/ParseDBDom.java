package com.wisdomelon.base.code.xml;

import com.wisdomelon.base.code.template.entity.Field;
import com.wisdomelon.base.code.sql.entity.Operate;
import org.dom4j.Element;

import java.util.List;

/**
 * @author wisdomelon
 * @date 2019/1/9 0009
 * @project fast
 * @jdk 1.8
 */
public interface ParseDBDom extends ParseDom{

    List<Field> parseCreateEle(String tableName, Element createElement);

    void parseTruncateEle(String tableName, Operate operate);

    void parseUpdateEle(String tableName, List<Element> updateList, Operate operate);

    void parseInsertEle(String tableName, List<Element> insertList, Operate operate);

    void parseDeleteEle(String tableName, List<Element> deleteList, Operate operate);

}
