package per.wph.bean;

import per.wph.Bus;
import per.wph.Car;
import per.wph.beans.BeanDefinitionReader;
import per.wph.beans.factory.BeanFactory;
import per.wph.beans.factory.XmlBeanFactory;
import per.wph.beans.io.ClassPathResource;
import per.wph.beans.xml.XmlBeanDefinitionReader;
import org.junit.Test;

/**
 * =============================================
 *
 * @author wu
 * @create 2018-07-22 20:58
 * =============================================
 */
public class BeanFactoryTest {
    @Test
    public void context() throws Exception {
        ClassPathResource resource = new ClassPathResource("bean.xml");
        BeanFactory beanFactory = new XmlBeanFactory();
        BeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinition(resource);
        Car object = (Car) beanFactory.getBean("car");
        System.out.println(object.getWheel().getBrand());
        Bus bus = (Bus) beanFactory.getBean("bus");
        System.out.println(bus.getAge());
    }
}
