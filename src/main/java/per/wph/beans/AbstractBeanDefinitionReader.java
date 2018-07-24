package per.wph.beans;

import per.wph.beans.factory.Registry;
import per.wph.beans.io.ResourceLoader;

public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader{
    public static final String BEAN_ELEMENT = "bean";

    public static final String PROPERTY_ELEMENT = "property";

    public static final String ID_ATTRIBUTE = "id";

    public static final String CLASS_ATTRIBUTE = "class";

    public static final String NAME_ATTRIBUTE = "name";

    public static final String VALUE_ATTRIBUTE = "value";

    public static final String REF_ATTRIBUTE = "ref";

    private Registry registry;

    private ResourceLoader resourceLoader;

    public AbstractBeanDefinitionReader(Registry registry, ResourceLoader resourceLoader) {
        this.registry = registry;
        this.resourceLoader = resourceLoader;
    }

    public Registry getRegistry() {
        return registry;
    }

    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }
}
