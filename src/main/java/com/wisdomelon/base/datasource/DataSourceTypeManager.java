package com.wisdomelon.base.datasource;

import com.wisdomelon.base.datasource.enums.DataSource;

/**
 * @author wisdomelon
 * @date 2019/1/10 0010
 * @project fast
 * @jdk 1.8
 */
public class DataSourceTypeManager {

    private static final ThreadLocal<DataSource> DATA_SOURCE_THREAD_LOCAL = new ThreadLocal<DataSource>(){

        @Override
        protected DataSource initialValue() {
            return DataSource.MASTER;
        }
    };

    public static DataSource get(){
        return DATA_SOURCE_THREAD_LOCAL.get();
    }

    public static void set(DataSource dataSourceType) {
        DATA_SOURCE_THREAD_LOCAL.set(dataSourceType);
    }

    public static void reset(){

        DATA_SOURCE_THREAD_LOCAL.set(DataSource.MASTER);
    }
}
