package per.wph.remote;

import per.wph.aop.BeanFactoryAware;
import per.wph.beans.factory.BeanDefinitionRegistry;
import per.wph.beans.factory.BeanFactory;
import per.wph.beans.io.Resource;
import per.wph.remote.factory.DefaultRemoteBeanFactory;
import per.wph.remote.factory.RemoteBeanFactory;

/**
 * =============================================
 *
 * @author wu
 * @create 2018-07-23 22:39
 * =============================================
 */
public final class RemoteClassLoader extends ClassLoader {


    private ByteLoaderStrategy strategy;
    private BeanDefinitionRegistry registry;

    public RemoteClassLoader(ByteLoaderStrategy strategy, BeanDefinitionRegistry registry) {
        super(Thread.currentThread().getContextClassLoader());
        this.strategy = strategy;
        this.registry = registry;
    }


    @Override
    protected Class<?> findClass(String name) {
        if(registry.containsBeanDefinition(name) && registry.getBeanDefinition(name) instanceof RemoteBeanDefinition){
            RemoteBeanDefinition remoteBeanDefinition = (RemoteBeanDefinition) registry.getBeanDefinition(name);
            return super.defineClass(name, strategy.load(remoteBeanDefinition), 0, strategy.load(remoteBeanDefinition).length);
        }
        throw new RuntimeException();
    }

}
