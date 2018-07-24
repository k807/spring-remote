package per.wph.beans.factory;

public interface BeanFactory extends Registry {
    Object getBean(String name) throws Exception;
}
