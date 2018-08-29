package per.wph.aop;

import org.junit.Assert;
import org.junit.Test;
import per.wph.Person;
import per.wph.aop.AspectJExpressionPointcut;
import per.wph.remote.factory.DefaultRemoteBeanFactory;

public class AspectJExpressionPointcutTest {

    @Test
    public void testClassFilter(){
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* per.wph.beans.*.*(..))");
        boolean result = pointcut.geteClassFilter().matches(DefaultRemoteBeanFactory.class);
        System.out.println(result);
    }


}
