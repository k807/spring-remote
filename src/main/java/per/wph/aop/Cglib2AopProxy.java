package per.wph.aop;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * =============================================
 *
 * @author wu
 * @create 2018-07-22 23:30
 * =============================================
 */
public class Cglib2AopProxy extends AbstractAopProxy implements MethodInterceptor {


    public Cglib2AopProxy(AdviceSupport adviceSupport) {
        super(adviceSupport);
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        if(adviceSupport.getMethodMatcher() != null &&
                adviceSupport.getMethodMatcher().matches(method, adviceSupport.getTargetSource().getTargetClass())){
            adviceSupport.getMethodInterceptor().invoke(new ReflectiveMethodInvocation(adviceSupport.getTargetSource().getTareget(),method,objects));
        }
        return method.invoke(adviceSupport.getTargetSource().getTareget(), objects);
    }

    @Override
    public Object getProxy() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(adviceSupport.getTargetSource().getTargetClass());
        enhancer.setInterfaces(adviceSupport.getTargetSource().getInterfaces());
        enhancer.setCallback(this);
        return enhancer.create();
    }
}
