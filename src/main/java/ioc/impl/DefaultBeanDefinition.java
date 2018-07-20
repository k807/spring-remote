package ioc.impl;


import ioc.*;

/**
 * =============================================
 *
 * @author wu
 * @create 2018-07-20 23:08
 * =============================================
 */
public class DefaultBeanDefinition implements BeanDefinition {
    private String beanName;
    private String beanClass;
    private Class<?> type;
    private PropertyValues propertyValues = new DefaultPropertyValues();
    private BeanReferences beanReferences;
    private Boolean isBeanPostProccessor;

    public void addPropertyValue(PropertyValue value){
        this.propertyValues.add(value);
    }

    public void addBeanReference(BeanReference ref){
        this.beanReferences.add(ref);
    }



    @Override
    public void markBeanPostProccessor() {
        this.isBeanPostProccessor = true;
    }

    @Override
    public String getBeanName() {
        return null;
    }

    @Override
    public Class<?> getType() {
        return null;
    }

    @Override
    public PropertyValues getPropertyValues() {
        return null;
    }

    @Override
    public BeanReferences getBeanReferences() {
        return beanReferences;
    }


    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    @Override
    public void setType(Class<?> type) {
        this.type = type;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }

    public void setBeanReferences(BeanReferences beanReferences) {
        this.beanReferences = beanReferences;
    }

    @Override
    public String getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(String beanClass) {
        this.beanClass = beanClass;
    }
}
