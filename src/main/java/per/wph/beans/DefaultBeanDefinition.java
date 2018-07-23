package per.wph.beans;


/**
 * =============================================
 *
 * @author wu
 * @create 2018-07-20 23:08
 * =============================================
 */
public class DefaultBeanDefinition implements BeanDefinition {
    private String beanName;
    private String beanClass;
    private Class<?> type;
    private PropertyValues propertyValues = new DefaultPropertyValues();
    private BeanReferences beanReferences = new DefaultBeanReferences();
    private Boolean isBeanPostProccessor;

    @Override
    public void addPropertyValue(PropertyValue value){
        this.propertyValues.add(value);
    }

    public void addBeanReference(BeanReference ref){
        this.beanReferences.add(ref);
    }



    @Override
    public void markBeanPostProccessor() {
        this.isBeanPostProccessor = true;
    }

    @Override
    public String getBeanName() {
        return beanName;
    }

    @Override
    public Class<?> getType() {
        return this.type;
    }

    @Override
    public PropertyValues getPropertyValues() {
        return this.propertyValues;
    }

    @Override
    public BeanReferences getBeanReferences() {
        return beanReferences;
    }


    @Override
    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    @Override
    public void setType(Class<?> type) {
        this.type = type;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }

    public void setBeanReferences(BeanReferences beanReferences) {
        this.beanReferences = beanReferences;
    }

    @Override
    public String getBeanClass() {
        return beanClass;
    }

    @Override
    public void setBeanClass(String beanClass) {
        this.beanClass = beanClass;
    }
}
