package abc.annotation.processe;

public interface BeanPostProcessor {
    //bean初始化前执行的业务
    Object postProcessBeforeInitialization(Object bean,String beanName);
    Object postProcessAfterInitialization(Object bean,String beanName);
}
