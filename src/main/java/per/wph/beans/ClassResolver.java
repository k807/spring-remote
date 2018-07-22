package per.wph.beans;

public interface ClassResolver {
    void resolver(BeanDefinition bean) throws ClassNotFoundException;
}
