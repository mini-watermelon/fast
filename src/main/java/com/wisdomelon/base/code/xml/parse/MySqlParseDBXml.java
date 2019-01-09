package com.wisdomelon.base.code.xml.parse;

import com.wisdomelon.base.code.template.entity.Module;
import com.wisdomelon.base.code.utils.FileUtils;
import com.wisdomelon.base.code.xml.ParseDBXml;
import com.wisdomelon.base.code.xml.ParseDom;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wisdomelon
 * @date 2019/1/8 0008
 * @project fast
 * @jdk 1.8
 */
public class MySqlParseDBXml implements ParseDBXml{

    private static final Logger LOGGER = Logger.getLogger(MySqlParseDBXml.class);

    @Override
    public List<Module> parse(String directory) {
        List<Module> moduleList = new ArrayList<>();

        //1. 根据路径信息获取具体的文件列表
        List<File> fileList = FileUtils.getFileList(directory, new ArrayList<>());
        if (fileList.size() == 0) {
            LOGGER.info("未找到需要解析的文件，本次解析结束。");
            return moduleList;
        }

        //2. 解析文件
        ParseDom parseDom = new MySqlParseDom();
        for (File file: fileList) {
            Module module = parseDom.parseDom(file);
            moduleList.add(module);
        }

        return moduleList;
    }
}
