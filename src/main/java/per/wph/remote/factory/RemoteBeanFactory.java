package per.wph.remote.factory;

import per.wph.beans.factory.BeanFactory;
import per.wph.remote.RemoteDBConfiguer;

public interface RemoteBeanFactory extends BeanFactory , RemoteDBConfiguer{
    String getBeanNameByRemote(String remoteName);
}
