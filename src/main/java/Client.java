import ioc.BeanDefinitionReader;
import ioc.BeanFactory;
import ioc.impl.ClassPathResource;
import ioc.impl.DefaultBeanFactory;
import ioc.impl.XmlBeanDefinitionReader;
import org.junit.Test;

/**
 * =============================================
 *
 * @author wu
 * @create 2018-07-21 0:11
 * =============================================
 */
public class Client {
    @Test
    public void context() throws Exception {
        ClassPathResource resource = new ClassPathResource("bean.xml");
        BeanFactory beanFactory = new DefaultBeanFactory();
        BeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinition(resource);

    }
}
