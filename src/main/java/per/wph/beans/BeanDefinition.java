package per.wph.beans;

public interface BeanDefinition {
    String getBeanName();
    String getBeanClass();
    Class<?> getType();
    PropertyValues getPropertyValues();
    BeanReferences getBeanReferences();

    void markBeanPostProccessor();

    void setType(Class<?> type);
}
