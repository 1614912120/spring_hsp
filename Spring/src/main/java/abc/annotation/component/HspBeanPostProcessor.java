package abc.annotation.component;

import abc.annotation.annotation.Component;
import abc.annotation.processe.BeanPostProcessor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@Component
public class HspBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        if(bean instanceof  Car) {
            System.out.println("这是一个car 我可以处理");
            //(Car)bean
        }
        System.out.println("postProcessBeforeInitialization被调用"+beanName+"bean="+bean.getClass());
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        System.out.println("postProcessAfterInitialization被调用"+beanName+"bean="+bean.getClass());
        //实现 AOP, 返回代理对象, 即对 bean 对象进行包装，不在返回原生的 bean
        if("smartDog".equals(beanName)) {
            Object proxyInstance =  Proxy.newProxyInstance(HspBeanPostProcessor.class.getClassLoader(),
                    bean.getClass().getInterfaces(),
                    new InvocationHandler() {
                        @Override
                        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                            System.out.println("method="+method.getName());
                            Object invoke = null;
                            if("getSum".equals(method.getName())) {
                                SmartAnimalAspect.showBeginLog();
                                //return method.invoke(bean, args);
                                invoke = method.invoke(bean, args);
                                SmartAnimalAspect.showSuccessEndLog();
                            } else {
                                invoke = method.invoke(bean, args);
                            }
                            return invoke;
                        }
                    });
            return proxyInstance;
        }
        return bean;
    }
}
