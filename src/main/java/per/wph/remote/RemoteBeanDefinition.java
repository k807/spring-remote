package per.wph.remote;

import per.wph.beans.BeanDefinition;

public class RemoteBeanDefinition extends BeanDefinition {
    private String remoteName;
    private String version;
    private boolean isRemote;

    public String getRemoteName() {
        return remoteName;
    }

    public void setRemoteName(String remoteName) {
        this.remoteName = remoteName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }


    public boolean isRemote() {
        return isRemote;
    }

    public void markRemote() {
        isRemote = true;
    }
}
