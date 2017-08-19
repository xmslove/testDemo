package com.bus.util.test;


import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;



@RunWith(SpringJUnit4ClassRunner.class) //使用junit4进行测试  
@ContextConfiguration(locations={"classpath*:spring-context-test.xml"}) //加载配置文件  
@Transactional  
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)//事物自动回滚，防止污染
public class BaseJunit4Test {
}
