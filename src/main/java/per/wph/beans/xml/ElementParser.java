package per.wph.beans.xml;

import org.w3c.dom.Element;
import per.wph.beans.BeanDefinition;


public interface ElementParser {
    BeanDefinition parse(BeanDefinition definition, Element ele);
}
