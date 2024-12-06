package abc.annotation.component;

import abc.annotation.annotation.Component;
import abc.annotation.processe.InitializingBean;

@Component
public class Car implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("car的初始化方法");
    }
}
