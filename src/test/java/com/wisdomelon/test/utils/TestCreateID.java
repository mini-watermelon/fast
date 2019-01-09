package com.wisdomelon.test.utils;

import com.wisdomelon.base.utils.generate.CreateID;
import org.junit.Test;

/**
 * @author wisdomelon
 * @date 2019/1/8 0008
 * @project fast
 * @jdk 1.8
 */
public class TestCreateID {

    @Test
    public void test1(){
        System.out.println(CreateID.getUniqueID());
        System.out.println(CreateID.getFilterUnqiueID());
        System.out.println(CreateID.getFilterUnqiueID("config"));
        System.out.println(CreateID.getUniqueID("sys"));
    }
}
