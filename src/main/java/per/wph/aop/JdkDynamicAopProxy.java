package per.wph.aop;

import org.aopalliance.intercept.MethodInterceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * =============================================
 *
 * @author wu
 * @create 2018-07-22 21:42
 * =============================================
 */
public class JdkDynamicAopProxy extends AbstractAopProxy implements InvocationHandler{

    public JdkDynamicAopProxy(AdviceSupport advise) {
        super(advise);
    }


    @Override
    public Object getProxy() {
        return Proxy.newProxyInstance(adviceSupport.getTargetSource().getTargetClass().getClassLoader(),
                adviceSupport.getTargetSource().getInterfaces(), this);
    }




    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        MethodInterceptor methodInterceptor = adviceSupport.getMethodInterceptor();
        if (adviceSupport.getMethodMatcher()!=null &&
                adviceSupport.getMethodMatcher().matches(method, adviceSupport.getTargetSource().getTargetClass())){
            methodInterceptor.invoke(new ReflectiveMethodInvocation(adviceSupport.getTargetSource().getTareget(), method, args));
            return method.invoke(adviceSupport.getTargetSource().getTareget(), args);
        }
        return method.invoke(adviceSupport.getTargetSource().getTareget(), args);
    }
}
