package ioc.impl;

import ioc.*;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Objects;
import java.util.Properties;

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

    private BeanDefinitionReader beanDefinitionReader;

    public DefaultBeanFactory() {
    }

    public void setBeanDefinitionReader(BeanDefinitionReader beanDefinitionReader) {
        this.beanDefinitionReader = beanDefinitionReader;
    }

    @Override
    public Object getBean(String name) throws IllegalAccessException, InstantiationException, NoSuchFieldException {
        Object object = singletonObjects.get(name);
        if(Objects.isNull(object)){
                // 1. 根据beanDefinition实例化bean
                BeanDefinition beanDefinition = beandefintitions.get(name);
                object = createBean(beanDefinition);

            }
        return object;
    }


    @Override
    public void registBeanDefinition(String name, BeanDefinition beanDefinition) {
        this.beandefintitions.put(name, beanDefinition);
    }

    public void refresh(){

    }

    private Object createBean(BeanDefinition beanDefinition) throws IllegalAccessException, InstantiationException, NoSuchFieldException {

        Object object;

        Class<?> type = beanDefinition.getType();

        // todo: 后续需要支持带参数构造方法
        object = type.newInstance();

        // todo: 先填充字符串常量，再初始化引用还是顺序无所谓？
        // 根据BeanReference准备好需要注入bean
        for(BeanReference beanReference : beanDefinition.getBeanReferences()){
            String ref = beanReference.getRef();
            Object refObject = getBean(ref);
            beanReference.setObject(refObject);
        }

        // todo: 基本类型转换, 现在仅支持基本数据类型
        // 基本类型转换
        for(PropertyValue value : beanDefinition.getPropertyValues()){

        }

        // 根据beanDefintion对对象进行注射
        populate(object, beanDefinition);

        // 将组装完成的bean注册到缓存
        singletonObjects.put(beanDefinition.getBeanName(), object);

        return object;

    }


    private void populate(Object object, BeanDefinition beanDefinition) throws NoSuchFieldException, IllegalAccessException {
        Class<?> type = beanDefinition.getType();
        // 成员变量名不能隐藏，方法参数名字可能隐藏
        for(BeanReference beanReference : beanDefinition.getBeanReferences()){
            Field field = type.getDeclaredField(beanReference.getName());
            field.setAccessible(true);
            field.set(object, beanReference.getObject());
        }

        for(PropertyValue value : beanDefinition.getPropertyValues()){
            Field field = type.getDeclaredField(value.getName());
            field.setAccessible(true);
            field.set(object, value.getValue());
        }

        // todo: 后续支持后续处理器
    }




}
