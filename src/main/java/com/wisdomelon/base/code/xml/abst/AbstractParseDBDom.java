package com.wisdomelon.base.code.xml.abst;

import com.wisdomelon.base.code.sql.entity.*;
import com.wisdomelon.base.code.template.entity.*;
import com.wisdomelon.base.code.xml.ParseDBDom;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.*;

/**
 * @author wisdomelon
 * @date 2019/1/9 0009
 * @project fast
 * @jdk 1.8
 */
public abstract class AbstractParseDBDom implements ParseDBDom{

    private static final Logger LOGGER = Logger.getLogger(AbstractParseDBDom.class);

    @Override
    public Module parseDom(File file) {
        SAXReader saxReader = new SAXReader();
        Module module = null;
        try {
            Document document = saxReader.read(file);
            Element root = document.getRootElement();
            String moduleName = root.attributeValue("moduleName") == null ? "" : root.attributeValue("moduleName");
            boolean code = root.attributeValue("code") == null ? false : Boolean.parseBoolean(root.attributeValue("code"));
            module = new Module(moduleName, code);
            List<Table> tableList = module.getTableList();

            for (Iterator<Element> elementIterator = root.elementIterator(); elementIterator.hasNext();) {
                Element element = elementIterator.next();
                String tableName = element.attributeValue("name") == null ? "" : element.attributeValue("name");
                if("".equals(tableName)){
                    continue;
                }
                String tableType = element.attributeValue("type") == null ? "create" : element.attributeValue("type");
                Boolean tableCode = element.attributeValue("code") == null ? null : Boolean.parseBoolean(element.attributeValue("type"));

                Element createElement = null;
                Element truncateElement = null;
                List<Element> insertList = new ArrayList<>();
                List<Element> updateList = new ArrayList<>();
                List<Element> deleteList = new ArrayList<>();
                for (Iterator<Element> operateIterator = element.elementIterator(); operateIterator.hasNext();) {
                    Element operate = operateIterator.next();
                    switch (operate.getName()){
                        case "create":
                            createElement = operate;
                            break;
                        case "truncate":
                            truncateElement = operate;
                            break;
                        case "insert":
                            insertList.add(operate);
                            break;
                        case "update":
                            updateList.add(operate);
                            break;
                        case "delete":
                            deleteList.add(operate);
                            break;
                    }
                }

                // 一切都以create存在为前提
                if(createElement != null) {
                    // create
                    Operate operate = new Operate(tableType);
                    String tableComment = createElement.attributeValue("comment") == null ? "" : createElement.attributeValue("comment");
                    List<Field> fieldList = parseCreateEle(tableName, createElement);

                    // truncate
                    if(truncateElement != null) {
                        parseTruncateEle(tableName, operate);
                    }

                    // insert
                    if(insertList.size() > 0) {
                        parseInsertEle(tableName, insertList, operate);
                    }

                    // update
                    if(updateList.size() > 0) {
                        parseUpdateEle(tableName, updateList, operate);
                    }

                    // delete
                    if(deleteList.size() > 0) {
                        parseDeleteEle(tableName, deleteList, operate);
                    }
                    tableList.add(new Table(moduleName, tableName, tableType, tableCode, tableComment, operate, fieldList));
                } else {
                    continue;
                }
            }
        } catch (DocumentException e) {
            LOGGER.error("解析XML文件错误");
        }

        return module;
    }

    @Override
    public List<Field> parseCreateEle(String tableName, Element createElement) {
        List<Field> fieldList = new ArrayList<>();
        for (Iterator<Element> fieldIterator = createElement.elementIterator(); fieldIterator.hasNext(); ) {
            Element fieldElement = fieldIterator.next();
            String fieldName = fieldElement.attributeValue("name") == null ? "" : fieldElement.attributeValue("name");
            if("".equals(fieldName)){
                continue;
            }
            String fieldType = fieldElement.attributeValue("type") == null ? "" : fieldElement.attributeValue("type");
            if("".equals(fieldType)){
                continue;
            }
            String fieldLength = fieldElement.attributeValue("length") == null ? "" : fieldElement.attributeValue("length");
            String fieldSpecial = fieldElement.attributeValue("special") == null ? "" : fieldElement.attributeValue("special");
            String fieldComment = fieldElement.attributeValue("comment") == null ? "" : fieldElement.attributeValue("comment");
            String defaultValue = fieldElement.attributeValue("default") == null ? "" : fieldElement.attributeValue("default");
            fieldList.add(new Field(tableName, fieldName, fieldType, fieldLength, fieldSpecial, defaultValue, fieldComment));
        }
        return fieldList;
    }

    @Override
    public void parseTruncateEle(String tableName, Operate operate) {
        operate.setTruncate(new Truncate(tableName));
    }

    @Override
    public void parseInsertEle(String tableName, List<Element> insertList, Operate operate) {
        List<Insert> operateInsertList = operate.getInsertList();
        for (Element insertElement: insertList) {
            List<String> listFieldName = new ArrayList<>();
            Map<String, String> mapFieldValue = new HashMap<>();
            Map<String, String> mapFieldStrategy = new HashMap<>();
            iteratorField(insertElement, listFieldName, mapFieldValue, mapFieldStrategy);
            if(listFieldName.size() > 0 && listFieldName.size() == insertElement.elements().size()) {
                operateInsertList.add(new Insert(tableName, listFieldName, mapFieldValue, mapFieldStrategy));
            }
        }
    }

    @Override
    public void parseUpdateEle(String tableName, List<Element> updateList, Operate operate) {
        List<Update> operateUpdateList = operate.getUpdateList();
        for (Element updateElement: updateList) {
            List<String> listFieldName = new ArrayList<>();
            Map<String, String> mapFieldValue = new HashMap<>();
            Map<String, String> mapFieldStrategy = new HashMap<>();
            Map<String, String> mapFieldWhere = new HashMap<>();
            String key = updateElement.attributeValue("key") == null ? "" : updateElement.attributeValue("key");
            mapFieldWhere.put(key, updateElement.attributeValue("value") == null ? "" : updateElement.attributeValue("value"));
            iteratorField(updateElement, listFieldName, mapFieldValue, mapFieldStrategy);
            if(listFieldName.size() > 0 && listFieldName.size() == updateElement.elements().size()) {
                operateUpdateList.add(new Update(tableName, listFieldName, mapFieldValue, mapFieldStrategy, mapFieldWhere));
            }
        }
    }

    @Override
    public void parseDeleteEle(String tableName, List<Element> deleteList, Operate operate) {
        List<Delete> operateDeleteList = operate.getDeleteList();
        for (Element deleteElement: deleteList) {
            Map<String, String> mapFieldWhere = new HashMap<>();
            String key = deleteElement.attributeValue("key") == null ? "" : deleteElement.attributeValue("key");
            mapFieldWhere.put(key, deleteElement.attributeValue("value") == null ? "" : deleteElement.attributeValue("value"));
            operateDeleteList.add(new Delete(tableName, mapFieldWhere));
        }
    }

    private void iteratorField(Element element, List<String> listFieldName, Map<String, String> mapFieldValue, Map<String, String> mapFieldStrategy) {
        for (Iterator<Element> fieldIterator = element.elementIterator(); fieldIterator.hasNext();) {
            Element fieldElement = fieldIterator.next();
            String name = fieldElement.attributeValue("name") == null ? "" : fieldElement.attributeValue("name");
            if("".equals(name)){
                break;
            }
            String value = fieldElement.attributeValue("value") == null ? "" : fieldElement.attributeValue("value");
            String strategy = fieldElement.attributeValue("strategy") == null ? "" : fieldElement.attributeValue("strategy");
            listFieldName.add(name);
            mapFieldValue.put(name, value);
            mapFieldStrategy.put(name, strategy);
        }
    }
}
