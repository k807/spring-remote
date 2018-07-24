package per.wph.beans;

import per.wph.beans.io.Resource;

public interface BeanDefinitionReader {
    void loadBeanDefinition(String location) throws Exception;
}
