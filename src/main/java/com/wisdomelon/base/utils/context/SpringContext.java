package com.wisdomelon.base.utils.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author wisdomelon
 * @date 2019/1/10 0010
 * @project fast
 * @jdk 1.8
 */
public class SpringContext implements ApplicationContextAware {

    private SpringContext(){}

    private ApplicationContext applicationContext;

    private static class SpringContextHolder {
        private static final SpringContext INSTANCE = new SpringContext();
    }

    public static final SpringContext getInstance() {
        return SpringContextHolder.INSTANCE;
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public <T> T getBean(Class<T> c){
        return applicationContext.getBean(c);
    }

    public Object getBean(String s){
        return applicationContext.getBean(s);
    }
}
