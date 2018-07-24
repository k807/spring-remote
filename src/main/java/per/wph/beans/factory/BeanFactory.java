package per.wph.beans.factory;

public interface BeanFactory extends BeanDefinitionRegistry {
    Object getBean(String name) throws Exception;
}
