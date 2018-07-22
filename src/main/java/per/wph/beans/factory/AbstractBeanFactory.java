package per.wph.beans.factory;

import per.wph.beans.*;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/**
 * =============================================
 * BeanFactory的基础实现类
 * @author wu
 * @create 2018-07-20 22:55
 * =============================================
 */
public abstract class AbstractBeanFactory implements BeanFactory {

    private HashMap<String, Object> singletonObjects = new HashMap<>();
    private HashMap<String, BeanDefinition> beandefintitions = new HashMap<>();
    private Set<BeanPostProcessor> beanPostProcessors = new LinkedHashSet<>();


    private BeanDefinitionReader beanDefinitionReader;

    public AbstractBeanFactory() {
    }

    public void setBeanDefinitionReader(BeanDefinitionReader beanDefinitionReader) {
        this.beanDefinitionReader = beanDefinitionReader;
    }

    @Override
    public Object getBean(String name) throws IllegalAccessException, InstantiationException, NoSuchFieldException {
        // 1. 从缓存获取object对象
        Object bean = singletonObjects.get(name);
        if(!Objects.isNull(bean)){
            return bean;
        }

        BeanDefinition beanDefinition = beandefintitions.get(name);
        if(beanDefinition == null){
            throw new IllegalArgumentException("No bean named " + name + " is defined");
        }
        bean = createBean(beanDefinition);
        return bean;
    }


    @Override
    public void registBeanDefinition(String name, BeanDefinition beanDefinition) {
        this.beandefintitions.put(name, beanDefinition);
    }

    private Object initializeBean(Object bean, String name) throws Exception {
        for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
            bean = beanPostProcessor.postProcessBeforeInitialization(bean, name);
        }

        for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
            bean = beanPostProcessor.postProcessAfterInitialization(bean, name);
        }
        return bean;
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
            Type fieldType = field.getType();

            // 基本数据类型转换
            if("".equals(value.getValue())){
            }else{
                if(fieldType.equals(String.class)){
                    field.set(object, value.getValue());
                }

                if(fieldType.equals(int.class)){
                    field.set(object, Integer.valueOf(value.getValue()));
                }

                if(fieldType.equals(Long.class)){
                    field.set(object, Long.valueOf(value.getValue()));
                }

                if(fieldType.equals(Float.class)){
                    field.set(object, Float.valueOf(value.getValue()));
                }

                if(fieldType.equals(Double.class)){
                    field.set(object, Double.valueOf(value.getValue()));
                }

                if(fieldType.equals(Character.class)){
                    field.set(object, Character.valueOf(value.getValue().toCharArray()[0]));
                }

                if(fieldType.equals(Byte.class)){
                    field.set(object, Byte.valueOf(value.getValue()));
                }
            }

        }

        // todo: 后续支持后续处理器
    }




}
