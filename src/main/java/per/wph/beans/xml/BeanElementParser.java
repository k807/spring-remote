package per.wph.beans.xml;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import per.wph.beans.BeanDefinition;
import per.wph.beans.DefaultBeanDefinition;
import per.wph.beans.DefaultBeanReference;
import per.wph.beans.DefaultPropertyValue;

/**
 * =============================================
 *
 * @author wu
 * @create 2018-07-24 0:14
 * =============================================
 */
public class BeanElementParser implements ElementParser{
    private static final String PARAM_ID = "id";
    private static final String PARAM_CLASS = "class";
    private static final String PARAM_PROPERTY = "property";
    @Override
    public BeanDefinition parse(BeanDefinition beanDefinition, Element ele) {
        // 1. 读取name和class
        String beanName = ele.getAttribute(PARAM_ID);
        String clazz = ele.getAttribute(PARAM_CLASS);
        beanDefinition.setBeanName(beanName);
        beanDefinition.setBeanClass(clazz);

        // 2. 读取property配置
        NodeList propertyNodes = ele.getElementsByTagName(PARAM_PROPERTY);
        for(int j = 0; j < propertyNodes.getLength(); j++) {
            Node node1 = propertyNodes.item(j);
            if(node1 instanceof org.w3c.dom.Element){
                org.w3c.dom.Element property = (org.w3c.dom.Element) node1;
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
        return beanDefinition;
    }
}
