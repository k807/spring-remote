package ioc.impl;

import ioc.BeanReference;

/**
 * =============================================
 *
 * @author wu
 * @create 2018-07-20 23:29
 * =============================================
 */
public class DefaultBeanReference implements BeanReference {
    private String name;
    private String ref;
    private Object object;



    public DefaultBeanReference(String name, String ref) {
        this.name = name;
        this.ref = ref;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getRef() {
        return ref;
    }

    @Override
    public void setObject(Object object) {
        this.object = object;
    }

    @Override
    public Object getObject() {
        return this.object;
    }
}
