package abc.annotation.processe;

//在bean得set后执行
public interface InitializingBean {
    //相当于初始化方法
    void afterPropertiesSet() throws Exception;
}
