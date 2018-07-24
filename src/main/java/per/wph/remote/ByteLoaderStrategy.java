package per.wph.remote;

public interface ByteLoaderStrategy {
    byte[] load(RemoteBeanDefinition beanDefinition);
}
