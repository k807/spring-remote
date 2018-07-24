package per.wph.beans.factory;

import per.wph.beans.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * =============================================
 * BeanFactory的基础实现类
 * @author wu
 * @create 2018-07-20 22:55
 * =============================================
 */
public abstract class AbstractBeanFactory implements BeanFactory {

    /**
     * 存储已经实例化的bean
     */
    private Map<String, Object> singletonObjects = new ConcurrentHashMap<>();

    /**
     * 存储beandefinitions
     */
    private HashMap<String, BeanDefinition> beandefintitions = new HashMap<>();

    /**
     * 存储beanPostProcessors
     */
    private List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

    /**
     * 存储的beanDefinitionNames
     */
    private volatile List<String> beanDefinitionNames = new ArrayList<>(256);

    /**
     * 尝试从cache中获取bean,如果存在则直接返回
     * 如果不存在则根据BeanDefinition创建新的bean
     * @param name bean的name, 在xml里面就是id
     * @return
     * @throws Exception
     */
    @Override
    public Object getBean(String name) throws Exception {
        // 1. 从缓存获取object对象
        Object bean = singletonObjects.get(name);
        if(!Objects.isNull(bean)){
            return bean;
        }

        // 2. 获取bendefinitions

        BeanDefinition beanDefinition = beandefintitions.get(name);
        if(beanDefinition == null){
            throw new IllegalArgumentException("No bean named " + name + " is defined");
        }

        //todo : initmethod

        // 3. 创建对象
        bean = doCreateBean(beanDefinition);
        bean = initializeBean(bean, name);
        beanDefinition.setBean(bean);
        singletonObjects.put(name, bean);
        return bean;
    }


    /**
     * 提供注册bean的接口
     * @param name
     * @param beanDefinition
     */
    @Override
    public void registBeanDefinition(String name, BeanDefinition beanDefinition) {
        this.beanDefinitionNames.add(name);
        this.beandefintitions.put(name, beanDefinition);
    }

    /**
     * 初始化bean
     * @param bean
     * @param name
     * @return
     * @throws Exception
     */
    protected Object initializeBean(Object bean, String name) throws Exception {
        for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
            bean = beanPostProcessor.postProcessBeforeInitialization(bean, name);
        }

        for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
            bean = beanPostProcessor.postProcessAfterInitialization(bean, name);
        }
        return bean;
    }

    /**
     * 创建bean的模板方法
     * @param beanDefinition
     * @return
     * @throws Exception
     */
    protected Object doCreateBean(BeanDefinition beanDefinition) throws Exception {
        /**
         * 创建原始bean对象
         */
        Object bean = createBeanInstance(beanDefinition);
        beanDefinition.setBean(bean);

        /**
         * 对bean进行注入
         */
        applyPropertyValues(bean, beanDefinition);

        beanDefinition.setBean(bean);
        return bean;
    }

    protected abstract void applyPropertyValues(Object bean, BeanDefinition beanDefinition) throws Exception;

    /**
     * 创建原始beanInstance
     * @param beanDefinition
     * @return
     * @throws Exception
     */
    protected Object createBeanInstance(BeanDefinition beanDefinition) throws Exception {
        return beanDefinition.getBeanClass().newInstance();
    }

    /**
     * 寻找所有type的子类
     * @param type
     * @return
     * @throws Exception
     */
    public List getBeansForType(Class type) throws Exception {
        List beans = new ArrayList<Object>();
        for(Map.Entry<String, BeanDefinition> entry : beandefintitions.entrySet()){
            if(type.isAssignableFrom(entry.getValue().getBeanClass())){
                beans.add(getBean(entry.getKey()));
            }
        }
        return beans;
    }


    /**
     * 添加beanPostProcessor
     * @param beanPostProcessor
     * @throws Exception
     */
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) throws Exception {
        this.beanPostProcessors.add(beanPostProcessor);
    }

    /**
     * 初始化所有bean
     * @throws Exception
     */
    public void preInstantiateSingletons() throws Exception {
        for (Iterator it = this.beanDefinitionNames.iterator(); it.hasNext();) {
            String beanName = (String) it.next();
            getBean(beanName);
        }
    }
}
