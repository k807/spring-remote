package per.wph.aop;

import org.junit.Test;
import per.wph.*;
import per.wph.beans.BeanDefinitionReader;
import per.wph.beans.factory.BeanFactory;
import per.wph.beans.factory.XmlBeanFactory;
import per.wph.beans.io.ClassPathResource;
import per.wph.beans.xml.XmlBeanDefinitionReader;
import org.junit.Before;

import java.lang.reflect.Method;

/**
 * =============================================
 *
 * @author wu
 * @create 2018-07-22 22:14
 * =============================================
 */
public class AopTest {
    BeanFactory beanFactory;

    @Before
    public void refresh() throws Exception {
        ClassPathResource resource = new ClassPathResource("bean.xml");
        beanFactory = new XmlBeanFactory();
        BeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinition(resource);
        Car object = (Car) beanFactory.getBean("car");
        Bus bus = (Bus) beanFactory.getBean("bus");
    }

//    @Test
//    public void jdkAopTest() throws Exception {
//        HelloWorldService service = (HelloWorldService) beanFactory.getBean("helloWorldService");
//
//        // 1. 设置需要代理的对象
//        TargetSource targetSource = new TargetSource(HelloWorldServiceImpl.class, new Class[]{HelloWorldService.class}, service);
//
//        // 2. 设置拦截器
//        AdviceSupport support = new AdviceSupport();
//        support.setTargetSource(targetSource);
//        support.setMethodInterceptor(new LogInterceptor());
//
//        // 3. 创建代理
//        JdkDynamicAopProxy jdkDynamicAopProxy = new JdkDynamicAopProxy(support);
//        HelloWorldService helloWorldService = (HelloWorldService) jdkDynamicAopProxy.getProxy();
//
//        helloWorldService.helloWorld();
//    }
//
//
//    @Test
//    public void cgligAopTest() throws Exception {
//        HelloWorldService service = (HelloWorldService) beanFactory.getBean("helloWorldService");
//
//        // 1. 设置需要代理的对象
//        TargetSource targetSource = new TargetSource(HelloWorldServiceImpl.class, new Class[]{HelloWorldService.class}, service);
//
//        // 2. 设置拦截器
//        AdviceSupport support = new AdviceSupport();
//        support.setTargetSource(targetSource);
//        support.setMethodInterceptor(new LogInterceptor());
//
//        // 3. 创建代理
//        JdkDynamicAopProxy jdkDynamicAopProxy = new JdkDynamicAopProxy(support);
//        HelloWorldService helloWorldService = (HelloWorldService) jdkDynamicAopProxy.getProxy();
//
//        helloWorldService.helloWorld();
//    }


}
