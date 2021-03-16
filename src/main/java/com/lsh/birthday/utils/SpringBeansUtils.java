package com.lsh.birthday.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 获取上下文的bean
 */
@Component
public class SpringBeansUtils implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringBeansUtils.applicationContext = applicationContext;
    }

    public static Object getBean(String name) {
        return SpringBeansUtils.applicationContext.getBean(name);

    }

    public static <T> T getBean(Class<T> clazz) {
        return SpringBeansUtils.applicationContext.getBean(clazz);
    }
}
