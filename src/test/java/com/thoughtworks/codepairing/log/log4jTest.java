package com.thoughtworks.codepairing.log;

import org.apache.log4j.Logger;
import org.junit.Test;


/**
 * @BelongsProject: joi-grad-shopping-cart-java-zhang
 * @BelongsPackage: com.thoughtworks.codepairing.log
 * @Author: ZhangZhaoru
 * @Date: 2021/6/8 10:51 上午
 * @Description:
 */
public class log4jTest {
    private static Logger logger = Logger.getLogger(log4jTest.class);

    @Test
    public void init() {
        logger.info("sout something!");
    }

}
