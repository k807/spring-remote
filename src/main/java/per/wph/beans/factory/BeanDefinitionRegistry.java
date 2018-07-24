package per.wph.beans.factory;

import per.wph.beans.BeanDefinition;

public interface BeanDefinitionRegistry {
    void registBeanDefinition(String beanName, BeanDefinition beanDefinition);

    BeanDefinition getBeanDefinition(String beanName);

    boolean containsBeanDefinition(String beanName);
}
