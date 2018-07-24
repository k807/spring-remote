package per.wph.beans.factory;

import per.wph.beans.BeanDefinition;

public interface Registry {
    void registBeanDefinition(String name, BeanDefinition beanDefinition);
}
