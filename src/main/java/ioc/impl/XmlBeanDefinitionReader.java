package ioc.impl;

import aop.BeanPostProcessor;
import ioc.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

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

        Element doc = document.getDocumentElement();
        NodeList nodeList = doc.getChildNodes();
        for(int i = 0; i <nodeList.getLength(); i++){
            Node node = nodeList.item(i);
            if(node instanceof Element){
                Element ele = (Element) node;
                if(ele.getNodeName().equals("bean")){
                    DefaultBeanDefinition beanDefinition = new DefaultBeanDefinition();
                    String name = ele.getAttribute("name");
                    String clazz = ele.getAttribute("clazz");
                    beanDefinition.setBeanName(name);
                    beanDefinition.setBeanClass(clazz);

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
                            }
                        }
                    }
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
        if(Objects.isNull(type)){
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
