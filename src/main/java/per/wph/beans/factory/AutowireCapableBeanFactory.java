package per.wph.beans.factory;

import per.wph.aop.BeanFactoryAware;
import per.wph.beans.BeanDefinition;
import per.wph.beans.BeanReference;
import per.wph.beans.PropertyValue;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * =============================================
 * 可自动装配Bean的beanFactory
 * @author wu
 * @create 2018-07-22 21:03
 * =============================================
 */
public class AutowireCapableBeanFactory extends AbstractBeanFactory {
    @Override
    protected void applyPropertyValues(Object bean, BeanDefinition mbd) throws Exception {
            if (bean instanceof BeanFactoryAware) {
                ((BeanFactoryAware) bean).setBeanFactory(this);
            }
            for (PropertyValue propertyValue : mbd.getPropertyValues().getPropertyValues()) {
                Object value = propertyValue.getValue();
                if (value instanceof BeanReference) {
                    BeanReference beanReference = (BeanReference) value;
                    value = getBean(beanReference.getName());
                }

                try {
                    Method declaredMethod = bean.getClass().getDeclaredMethod(
                            "set" + propertyValue.getName().substring(0, 1).toUpperCase()
                                    + propertyValue.getName().substring(1), value.getClass());
                    declaredMethod.setAccessible(true);

                    declaredMethod.invoke(bean, value);
                } catch (NoSuchMethodException e) {
                    Field declaredField = bean.getClass().getDeclaredField(propertyValue.getName());
                    declaredField.setAccessible(true);
                    declaredField.set(bean, value);
                }
            }
    }
}
