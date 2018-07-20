package ioc.impl;

/**
 * =============================================
 *
 * @author wu
 * @create 2018-07-20 23:29
 * =============================================
 */
public class DefaultBeanReference {
    private String name;
    private String ref;

    public String getName() {
        return name;
    }

    public String getRef() {
        return ref;
    }

    public DefaultBeanReference(String name, String ref) {
        this.name = name;
        this.ref = ref;
    }
}
