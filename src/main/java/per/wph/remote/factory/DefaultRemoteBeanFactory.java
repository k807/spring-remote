package per.wph.remote.factory;

import per.wph.beans.BeanDefinition;
import per.wph.beans.factory.AutowireCapableBeanFactory;
import per.wph.remote.ByteLoaderStrategy;
import per.wph.remote.RemoteBeanDefinition;
import per.wph.remote.RemoteClassLoader;

import java.util.concurrent.ConcurrentHashMap;

public class DefaultRemoteBeanFactory extends AutowireCapableBeanFactory implements RemoteBeanFactory {

    private RemoteClassLoader remoteClassLoader;

    private ConcurrentHashMap<String,String> remote2beanName = new ConcurrentHashMap<>();

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
            if(remote.getRemoteName() != null && remote.getRemoteName() != ""){
                Class realClass = remoteClassLoader.loadClass(getBeanNameByRemote(remote.getRemoteName()));
                return doCreateBeanInstance(realClass, getBeanNameByRemote(remote.getRemoteName()));
            }
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

    public void registRemoteName(String remoteName, String beanName) {
        this.remote2beanName.put(remoteName, beanName);
    }

    @Override
    public String getBeanNameByRemote(String beanName) {
        return this.remote2beanName.get(beanName);
    }

    @Override
    public void registBeanDefinition(String name, BeanDefinition beanDefinition) {
        if(beanDefinition instanceof RemoteBeanDefinition){
            RemoteBeanDefinition remote = (RemoteBeanDefinition) beanDefinition;
            if(remote.getRemoteName() != null){
                registRemoteName(remote.getRemoteName(), name);
            }
        }
        super.registBeanDefinition(name, beanDefinition);
    }
}
