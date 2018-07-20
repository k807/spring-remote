package ioc.impl;

import ioc.BeanDefinition;
import ioc.BeanDefinitionReader;
import ioc.BeanFactory;
import java.util.HashMap;

/**
 * =============================================
 *
 * @author wu
 * @create 2018-07-20 22:55
 * =============================================
 */
public class DefaultBeanFactory implements BeanFactory {

    private HashMap<String, Object> singletonObjects = new HashMap<String, Object>();
    private HashMap<String, BeanDefinition> beandefintitions = new HashMap<String, BeanDefinition>();

    BeanDefinitionReader beanDefinitionReader;

    public DefaultBeanFactory() {
    }

    public void setBeanDefinitionReader(BeanDefinitionReader beanDefinitionReader) {
        this.beanDefinitionReader = beanDefinitionReader;
    }

    @Override
    public Object getBean(String name) {
        return singletonObjects.get(name);
    }

    @Override
    public void registBeanDefinition(String name, BeanDefinition beanDefinition) {
        this.beandefintitions.put(name, beanDefinition);
    }

    public void refresh(){

    }



}
