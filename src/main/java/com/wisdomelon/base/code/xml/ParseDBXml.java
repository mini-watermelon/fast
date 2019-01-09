package com.wisdomelon.base.code.xml;

import com.wisdomelon.base.code.template.entity.Module;

import java.util.List;

/**
 * @author wisdomelon
 * @date 2019/1/8 0008
 * @project fast
 * @jdk 1.8
 */
@FunctionalInterface
public interface ParseDBXml {

    List<Module> parse(String directory);
}
