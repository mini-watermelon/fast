package com.wisdomelon.base.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author wisdomelon
 * @date 2019/1/10 0010
 * @project fast
 * @jdk 1.8
 */
public class ThreadLocalRountingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceTypeManager.get();
    }
}
