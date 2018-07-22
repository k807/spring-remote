package per.wph.aop;

import org.aopalliance.intercept.MethodInterceptor;

/**
 * =============================================
 * 被代理对象
 * 1. 被代理对象
 * 2. 拦截器
 * 3. 方法匹配器
 * @author wu
 * @create 2018-07-22 21:32
 * =============================================
 */
public class AdviceSupport {
    private TargetSource targetSource;

    private MethodInterceptor methodInterceptor;

    private MethodMatcher methodMatcher;

    public TargetSource getTargetSource() {
        return targetSource;
    }

    public void setTargetSource(TargetSource targetSource) {
        this.targetSource = targetSource;
    }

    public MethodInterceptor getMethodInterceptor() {
        return methodInterceptor;
    }

    public void setMethodInterceptor(MethodInterceptor methodInterceptor) {
        this.methodInterceptor = methodInterceptor;
    }

    public MethodMatcher getMethodMatcher() {
        return methodMatcher;
    }

    public void setMethodMatcher(MethodMatcher methodMatcher) {
        this.methodMatcher = methodMatcher;
    }
}
