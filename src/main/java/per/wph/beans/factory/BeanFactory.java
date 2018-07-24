package per.wph.beans.factory;

public interface BeanFactory extends BeanDefinitionRegistry {
    <T> T getBean(String name) throws Exception;
}
