package per.wph.beans;

import per.wph.beans.factory.Registry;
import per.wph.beans.io.ResourceLoader;

public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader{
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
