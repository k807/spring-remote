package per.wph.remote;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import per.wph.beans.AbstractBeanDefinitionReader;
import per.wph.beans.BeanDefinition;
import per.wph.beans.BeanReference;
import per.wph.beans.PropertyValue;
import per.wph.beans.factory.BeanDefinitionRegistry;
import per.wph.beans.io.ResourceLoader;
import per.wph.remote.factory.RemoteBeanFactory;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;

public class RemoteBeanDefinitionReader extends AbstractBeanDefinitionReader {
    protected static final String DB_PROPERTY_ELEMENT = "db-property";


    private RemoteDBConfiguer dbConfiguer;

    public RemoteBeanDefinitionReader(RemoteBeanFactory beanFactory, ResourceLoader resourceLoader) {
        super(beanFactory, resourceLoader);
        this.dbConfiguer = beanFactory;
    }

    @Override
    public void loadBeanDefinition(String location) throws Exception {
        InputStream inputStream = getResourceLoader().getResource(location).getInputStream();
        doLoadBeanDefinitions(inputStream);
    }

    protected void doLoadBeanDefinitions(InputStream inputStream) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = factory.newDocumentBuilder();
        Document doc = docBuilder.parse(inputStream);
        // 解析bean
        registerBeanDefinitions(doc);
        inputStream.close();
    }

    /**
     * 向Registry注册BeanDefinition
     *
     * @param doc
     */
    public void registerBeanDefinitions(Document doc) {
        Element root = doc.getDocumentElement();

        parseBeanDefinitions(root);
    }

    /**
     * parse beans节点下的bean节点
     *
     * @param root
     */
    protected void parseBeanDefinitions(Element root) {
        NodeList nl = root.getChildNodes();
        for (int i = 0; i < nl.getLength(); i++) {
            Node node = nl.item(i);
            if (node instanceof Element) {
                Element ele = (Element) node;
                switch (ele.getTagName()) {
                    case BEAN_ELEMENT:
                        processBeanDefinition(ele);
                        break;
                    case DB_PROPERTY_ELEMENT:
                        processDBElement(ele);
                        break;
                        default:
                            break;
                }
            }
        }
    }


    /**
     * parse <bean><bean/> element
     *
     * @param ele
     */
    protected void processBeanDefinition(Element ele) {
        String name = ele.getAttribute(ID_ATTRIBUTE);
        String className = ele.getAttribute(CLASS_ATTRIBUTE);
        String remoteName = ele.getAttribute("remote-name");
        String remoteVersion = ele.getAttribute("version");

        RemoteBeanDefinition beanDefinition = new RemoteBeanDefinition();
        beanDefinition.setBeanClassName(className);
        // 默认使用AppClassLoader加载类文件

        if(remoteName != null && !"".equals(remoteName)){
            beanDefinition.markRemote();
            beanDefinition.setRemoteName(remoteName);
            beanDefinition.setVersion(remoteVersion);
        }

        try {
            if(!beanDefinition.isRemote()){
                Class<?> clazz = Class.forName(className);
                beanDefinition.setBeanClass(clazz);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        processProperty(ele, beanDefinition);
        getBeanDefinitionRegistry().registBeanDefinition(name, beanDefinition);
    }


    /**
     * parse <db-property><db-property/> element
     */
    private void processDBElement(Element ele) {
        String driver = ele.getElementsByTagName("driver").item(0).getTextContent();
        String url = ele.getElementsByTagName("url").item(0).getTextContent();
        String username = ele.getElementsByTagName("username").item(0).getTextContent();
        String password = ele.getElementsByTagName("password").item(0).getTextContent();

        dbConfiguer.configureDriver(driver);
        dbConfiguer.configureHost(url);
        dbConfiguer.configureUsername(username);
        dbConfiguer.configurePassowrd(password);
    }

    /**
     * parse <property><property/> element
     *
     * @param ele
     * @param beanDefinition
     */
    private void processProperty(Element ele, BeanDefinition beanDefinition) {
        NodeList propertyNode = ele.getElementsByTagName(PROPERTY_ELEMENT);
        for (int i = 0; i < propertyNode.getLength(); i++) {
            Node node = propertyNode.item(i);
            if (node instanceof Element) {
                Element propertyEle = (Element) node;
                String name = propertyEle.getAttribute(NAME_ATTRIBUTE);
                String value = propertyEle.getAttribute(VALUE_ATTRIBUTE);
                if (value != null && value.length() > 0) {
                    beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name, value));
                } else {
                    String ref = propertyEle.getAttribute(REF_ATTRIBUTE);
                    if (ref == null || ref.length() == 0) {
                        throw new IllegalArgumentException("Configuration problem: <property> element for property '"
                                + name + "' must specify a ref or value");
                    }
                    BeanReference beanReference = new BeanReference(ref);
                    beanDefinition.getPropertyValues().addPropertyValue(new PropertyValue(name, beanReference));
                }
            }
        }
    }
}
