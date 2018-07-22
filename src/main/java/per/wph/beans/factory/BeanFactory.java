package per.wph.beans.factory;

public interface BeanFactory extends Registrar {
    Object getBean(String name) throws IllegalAccessException, InstantiationException, NoSuchFieldException;
}
