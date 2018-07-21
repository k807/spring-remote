package ioc;

public interface BeanReference{
    void setObject(Object object);
    Object getObject();
    String getName();
    String getRef();
}
