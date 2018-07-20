package ioc;

public interface BeanFactory extends Registrar{
    Object getBean(String name);
}
