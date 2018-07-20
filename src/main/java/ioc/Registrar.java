package ioc;

public interface Registrar {
    void registBeanDefinition(String name, BeanDefinition beanDefinition);
}
