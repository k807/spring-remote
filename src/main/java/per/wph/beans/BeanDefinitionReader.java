package per.wph.beans;

import per.wph.beans.io.Resource;

public interface BeanDefinitionReader {
    public static final String BEAN_ELEMENT = "bean";

    public static final String PROPERTY_ELEMENT = "property";

    public static final String ID_ATTRIBUTE = "id";

    public static final String CLASS_ATTRIBUTE = "class";

    public static final String NAME_ATTRIBUTE = "name";

    public static final String VALUE_ATTRIBUTE = "value";

    public static final String REF_ATTRIBUTE = "ref";

    void loadBeanDefinition(String location) throws Exception;
}
