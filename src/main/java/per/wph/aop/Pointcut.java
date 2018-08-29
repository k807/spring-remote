package per.wph.aop;

public interface Pointcut {
    ClassFilter geteClassFilter();
    MethodMatcher getMethodMatcher();
}
