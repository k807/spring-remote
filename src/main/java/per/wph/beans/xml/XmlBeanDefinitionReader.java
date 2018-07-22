package per.wph.beans.xml;

import per.wph.beans.BeanPostProcessor;
import per.wph.beans.DefaultBeanDefinition;
import per.wph.beans.DefaultBeanReference;
import per.wph.beans.DefaultClassResolver;
import per.wph.beans.DefaultPropertyValue;
import per.wph.beans.factory.Registrar;
import per.wph.beans.io.Resource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import per.wph.beans.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.Objects;

/**
 * =============================================
 *
 * @author wu
 * @create 2018-07-20 23:00
 * =============================================
 */
public class XmlBeanDefinitionReader implements BeanDefinitionReader {
    private Registrar registrar;

    private ClassResolver resolver = new DefaultClassResolver();

    public void setResolver(ClassResolver resolver) {
        this.resolver = resolver;
    }

    public XmlBeanDefinitionReader(Registrar registrar) {
        this.registrar = registrar;
    }

    @Override
    public void loadBeanDefinition(Resource resource) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(resource.getInputStream());

        // 1. 获得xml的document文件
        Element doc = document.getDocumentElement();
        NodeList nodeList = doc.getChildNodes();
        for(int i = 0; i <nodeList.getLength(); i++){
            Node node = nodeList.item(i);
            if(node instanceof Element){
                Element ele = (Element) node;
                if(ele.getNodeName().equals("bean")){
                    // 1. 读取name和class
                    DefaultBeanDefinition beanDefinition = new DefaultBeanDefinition();
                    String beanName = ele.getAttribute("id");
                    String clazz = ele.getAttribute("class");
                    beanDefinition.setBeanName(beanName);
                    beanDefinition.setBeanClass(clazz);

                    // 2. 读取property配置
                    NodeList propertyNodes = ele.getElementsByTagName("property");
                    for(int j = 0; j < propertyNodes.getLength(); j++) {
                        Node node1 = propertyNodes.item(j);
                        if(node1 instanceof Element){
                            Element property = (Element) node1;
                            String propertyName = property.getAttribute("name");
                            if (property.hasAttribute("value")){
                                String value = property.getAttribute("value");
                                DefaultPropertyValue propertyValue = new DefaultPropertyValue(propertyName, value);
                                beanDefinition.addPropertyValue(propertyValue);
                            }
                            if(property.hasAttribute("ref")){
                                String ref = property.getAttribute("ref");
                                DefaultBeanReference reference = new DefaultBeanReference(propertyName, ref);
                                beanDefinition.addBeanReference(reference);
                            }
                        }
                    }

                    // 3. 解析class类型
                    resolveBeanDefinition(beanDefinition);
                    registrar.registBeanDefinition(beanDefinition.getBeanName(), beanDefinition);
                }

            }
        }

    }


    private void resolveBeanDefinition(BeanDefinition beanDefinition) throws ClassNotFoundException {
        if(!Objects.isNull(resolver)){
            resolver.resolver(beanDefinition);
        }

        Class type = beanDefinition.getType();
        if(!Objects.isNull(type)){
            if(type.getInterfaces() != null){
                for(Class inter : type.getInterfaces()){
                    if(inter == BeanPostProcessor.class){
                        beanDefinition.markBeanPostProccessor();
                        return;
                    }
                }
            }
        }
    }
}
