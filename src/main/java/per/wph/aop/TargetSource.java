package per.wph.aop;

/**
 * =============================================
 * 被代理的对象
 * @author wu
 * @create 2018-07-22 21:33
 * =============================================
 */
public class TargetSource {
    private Class<?> targetClass;

    private Class<?>[] interfaces;

    private Object tareget;

    public TargetSource(Class<?> targetClass, Class<?>[] interfaces, Object tareget) {
        this.targetClass = targetClass;
        this.interfaces = interfaces;
        this.tareget = tareget;
    }

    public Class<?> getTargetClass() {
        return targetClass;
    }

    public Class<?>[] getInterfaces() {
        return interfaces;
    }

    public Object getTareget() {
        return tareget;
    }
}
