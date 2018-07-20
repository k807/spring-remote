package ioc;

public interface ClassResolver {
    void resolver(BeanDefinition bean) throws ClassNotFoundException;
}
