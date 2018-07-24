package per.wph.remote;

import org.junit.Before;
import org.junit.Test;
import per.wph.Father;
import per.wph.FlyService;
import per.wph.HelloWorldService;
import per.wph.Person;
import per.wph.beans.factory.BeanFactory;
import per.wph.beans.io.ResourceLoader;
import per.wph.remote.factory.DefaultRemoteBeanFactory;

/**
 * =============================================
 *
 * @author wu
 * @create 2018-07-25 0:24
 * =============================================
 */
public class RemoteBeanFactoryTest {

    BeanFactory beanFactory;

    @Before
    public void before() throws Exception {
        DatabaseStrategy strategy = new DatabaseStrategy();
        DefaultRemoteBeanFactory factory = new DefaultRemoteBeanFactory(strategy);
        strategy.setDbConfiguer(factory);

        ResourceLoader resourceLoader = new ResourceLoader();
        RemoteBeanDefinitionReader reader = new RemoteBeanDefinitionReader(factory, resourceLoader);
        reader.loadBeanDefinition("beans.xml");

        this.beanFactory = factory;

    }

    @Test
    public void testBase() throws Exception {

        HelloWorldService helloWorldService = beanFactory.getBean("remote");
        helloWorldService.helloWorld();

    }

    @Test
    public void testPopulta() throws Exception {
        Person person = beanFactory.getBean("person");
        person.invoke();
    }

    @Test
    public void testVersion() throws Exception {
        HelloWorldService helloWorldService = beanFactory.getBean("remote");
        helloWorldService.helloWorld();
    }

    @Test
    public void testNewClass() throws Exception {
        FlyService flyService = beanFactory.getBean("fly");
        flyService.fly("heihei");
    }

    @Test
    public void testInherit() throws Exception {
        Father father = beanFactory.getBean("son");
        father.show();
    }
}
