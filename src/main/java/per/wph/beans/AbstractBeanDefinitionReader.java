package per.wph.beans;

import per.wph.beans.factory.BeanDefinitionRegistry;
import per.wph.beans.io.ResourceLoader;

public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader{
    public static final String BEAN_ELEMENT = "bean";

    public static final String PROPERTY_ELEMENT = "property";

    public static final String ID_ATTRIBUTE = "id";

    public static final String CLASS_ATTRIBUTE = "class";

    public static final String NAME_ATTRIBUTE = "name";

    public static final String VALUE_ATTRIBUTE = "value";

    public static final String REF_ATTRIBUTE = "ref";

    private BeanDefinitionRegistry beanDefinitionRegistry;

    private ResourceLoader resourceLoader;

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegistry) {
        this.beanDefinitionRegistry = beanDefinitionRegistry;
    }

    public BeanDefinitionRegistry getBeanDefinitionRegistry() {
        return beanDefinitionRegistry;
    }

    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }
}
