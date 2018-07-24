package per.wph.remote.factory;

import per.wph.beans.BeanDefinition;
import per.wph.beans.factory.AbstractBeanFactory;
import per.wph.beans.factory.AutowireCapableBeanFactory;
import per.wph.remote.ByteLoaderStrategy;
import per.wph.remote.RemoteBeanDefinition;
import per.wph.remote.RemoteClassLoader;

public class DefaultRemoteBeanFactory extends AutowireCapableBeanFactory implements RemoteBeanFactory {

    private RemoteClassLoader remoteClassLoader;

    private String driver;
    private String host;
    private String username;
    private String password;

    public DefaultRemoteBeanFactory(ByteLoaderStrategy strategy) {
        this.remoteClassLoader = new RemoteClassLoader(strategy, this);
    }

    @Override
    protected Object createBeanInstance(BeanDefinition beanDefinition) throws Exception {
        if (beanDefinition instanceof RemoteBeanDefinition) {
            RemoteBeanDefinition remote = (RemoteBeanDefinition) beanDefinition;
            Class realClass = remoteClassLoader.loadClass(remote.getRemoteName());
            return doCreateBeanInstance(realClass, remote.getRemoteName());
        }
        return super.createBeanInstance(beanDefinition);
    }


    private Object doCreateBeanInstance(Class<?> type, String remoteName) throws Exception {
        return type.newInstance();
    }




    @Override
    public void configureDriver(String driver) {
        this.driver = driver;
    }

    @Override
    public void configureHost(String host) {
        this.host = host;
    }

    @Override
    public void configureUsername(String username) {
        this.username = username;
    }

    @Override
    public void configurePassowrd(String password) {
        this.password = password;
    }

    @Override
    public String getDriver() {
        return driver;
    }

    @Override
    public String getHost() {
        return host;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }
}
