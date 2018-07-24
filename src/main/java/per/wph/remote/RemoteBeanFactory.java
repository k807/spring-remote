package per.wph.remote;

import per.wph.beans.BeanDefinition;
import per.wph.beans.factory.AbstractBeanFactory;
import per.wph.beans.factory.AutowireCapableBeanFactory;

public class RemoteBeanFactory extends AutowireCapableBeanFactory {

    private RemoteClassLoader remoteClassLoader;

    public RemoteBeanFactory(RemoteClassLoader remoteClassLoader) {
        this.remoteClassLoader = remoteClassLoader;
    }

    @Override
    protected Object createBeanInstance(BeanDefinition beanDefinition) throws Exception {
        if (beanDefinition instanceof RemoteBeanDefinition) {
            RemoteBeanDefinition remoteDef = (RemoteBeanDefinition) beanDefinition;

        }
        return super.createBeanInstance(beanDefinition);
    }


}
