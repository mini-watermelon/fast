package com.wisdomelon.base.code.xml;

import com.wisdomelon.base.code.template.entity.Module;

import java.io.File;

/**
 * @author wisdomelon
 * @date 2019/1/9 0009
 * @project fast
 * @jdk 1.8
 */
public interface ParseDom {

    Module parseDom(File file);

}
