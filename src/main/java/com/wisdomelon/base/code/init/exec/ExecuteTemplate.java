package com.wisdomelon.base.code.init.exec;

import com.wisdomelon.base.code.init.abst.AbstractExecute;
import com.wisdomelon.base.code.template.entity.Module;
import org.apache.log4j.Logger;

import java.net.URL;
import java.util.List;

/**
 * @author wisdomelon
 * @date 2019/1/9 0009
 * @project fast
 * @jdk 1.8
 */
public class ExecuteTemplate extends AbstractExecute{

    private static final Logger LOGGER = Logger.getLogger(ExecuteTemplate.class);

    private List<Module> moduleList;

    private ExecuteTemplate(){}

    private static class ExecuteTemplateHolder {
        private static final ExecuteTemplate INSTANCE = new ExecuteTemplate();
    }

    public static final ExecuteTemplate getInstance() {
        return ExecuteTemplateHolder.INSTANCE;
    }

    public static final ExecuteTemplate getInstance(List<Module> moduleList, String templateDir) {
        ExecuteTemplate instance = ExecuteTemplateHolder.INSTANCE;
        instance.setModuleList(moduleList);
        instance.setTemplateDir(templateDir);
        return instance;
    }

    public List<Module> getModuleList() {
        return moduleList;
    }

    public void setModuleList(List<Module> moduleList) {
        this.moduleList = moduleList;
    }

    @Override
    public void execute() {

        if("".equals(templateDir) || null == templateDir) {
            // 说明没有配置这个路径，则尝试从默认classpath中去取
            URL resource = Thread.currentThread().getContextClassLoader().getResource("template/");
            if(resource == null) {
                // 说明路径不存在
                LOGGER.info("未配置template模板文件路径，且默认路径不存在，不执行创建模板文件操作，本次解析结束。");
                return;
            } else {
                templateDir = resource.getPath();
            }
        }

    }
}
