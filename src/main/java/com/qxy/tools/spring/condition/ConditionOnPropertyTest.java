package com.qxy.tools.spring.condition;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: 类信息描述
 * @Author: qinxiaoyun001@lianjia.com
 * @Date: 2019/10/16 9:37 AM
 * @Version: 1.0
 */

@Configuration
@ConditionalOnProperty(value = "parent-name")
public class ConditionOnPropertyTest {

}
