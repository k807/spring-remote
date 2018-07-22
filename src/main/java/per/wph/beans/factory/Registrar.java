package per.wph.beans.factory;

import per.wph.beans.BeanDefinition;

public interface Registrar {
    void registBeanDefinition(String name, BeanDefinition beanDefinition);
}
