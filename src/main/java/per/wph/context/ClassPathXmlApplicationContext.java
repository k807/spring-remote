package per.wph.context;

import per.wph.beans.BeanDefinition;
import per.wph.beans.factory.AbstractBeanFactory;
import per.wph.beans.factory.AutowireCapableBeanFactory;
import per.wph.beans.io.ResourceLoader;
import per.wph.beans.xml.XmlBeanDefinitionReader;

public class ClassPathXmlApplicationContext extends AbstractApplicationContext {



    private String configLocation;

    public ClassPathXmlApplicationContext(String configLocation) throws Exception {
        this(configLocation, new AutowireCapableBeanFactory());
    }

    public ClassPathXmlApplicationContext(String configLocation, AbstractBeanFactory beanFactory) throws Exception {
        super(beanFactory);
        this.configLocation = configLocation;
        refresh();
    }

    @Override
    protected void loadBeanDefinitions(AbstractBeanFactory beanFactory) throws Exception {
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(this.beanFactory , new ResourceLoader());
        reader.loadBeanDefinition(configLocation);
    }
}
