package per.wph.aop;

import java.lang.reflect.Method;

public interface ClassFilter {
    boolean matches(Class targetClass);
}
