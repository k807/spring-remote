package per.wph.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * =============================================
 *
 * @author wu
 * @create 2018-07-22 21:37
 * =============================================
 */
public class LogInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        System.out.println("Log start time : " + System.nanoTime());
        Object ret = methodInvocation.proceed();
        System.out.println("Log end time : " + System.nanoTime());
        return ret;
    }
}
