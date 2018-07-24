package per.wph.beans;

public interface BeanPostProcessor<T> {

    /**
     * 前置处理器
     * @param bean
     * @param beanName
     * @return
     * @throws Exception
     */
    T postProcessBeforeInitialization(T bean, String beanName) throws Exception;

    /**
     * 后置处理器
     * @param bean
     * @param beanName
     * @return
     * @throws Exception
     */
    T postProcessAfterInitialization(T bean, String beanName) throws Exception;
}
