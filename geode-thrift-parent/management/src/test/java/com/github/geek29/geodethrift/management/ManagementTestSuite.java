package com.github.geek29.geodethrift.management;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ CommandExecutorTest.class, GeodeManagementHandlerTest.class,
		ResultBuilderTest.class, ThriftProcessorTest.class })
public class ManagementTestSuite {

}
