package per.wph.beans;

public interface BeanDefinition {
    String getBeanName();
    void setBeanName(String name);

    String getBeanClass();
    void setBeanClass(String clazz);

    Class<?> getType();
    void setType(Class<?> type);

    PropertyValues getPropertyValues();
    void addPropertyValue(PropertyValue propertyValue);

    BeanReferences getBeanReferences();
    void addBeanReference(BeanReference reference);

    void markBeanPostProccessor();
}
