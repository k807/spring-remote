package ioc;

public interface BeanDefinitionReader {
    void loadBeanDefinition(Resource resource) throws Exception;
}
