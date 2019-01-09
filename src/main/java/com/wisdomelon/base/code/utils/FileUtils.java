package com.wisdomelon.base.code.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wisdomelon
 * @date 2019/1/9 0009
 * @project fast
 * @jdk 1.8
 */
public class FileUtils {

    /***
     * 递归获取根路径下所有xml文件
     * @param dir
     * @param list
     * @return
     */
    public static List<File> getFileList(String dir, List<File> list) {
        File files = new File(dir);
        File[] listFiles = files.listFiles();
        if(listFiles != null && listFiles.length > 0) {
            for(File file : listFiles) {
                if(file.isDirectory()) {
                    list.addAll(getFileList(file.getPath(), new ArrayList<>()));
                } else if(file.isFile()) {
                    if(file.getName().lastIndexOf(".xml") > 0) {
                        list.add(file);
                    }
                }

            }
        }
        return list;
    }
}
