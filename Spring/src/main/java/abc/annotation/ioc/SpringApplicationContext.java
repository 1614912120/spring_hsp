package abc.annotation.ioc;

import abc.annotation.annotation.Autowired;
import abc.annotation.annotation.Component;
import abc.annotation.annotation.ComponentScan;
import abc.annotation.annotation.Scope;
import abc.annotation.processe.BeanPostProcessor;
import abc.annotation.processe.InitializingBean;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class SpringApplicationContext {
    private Class configClass;
    //定义map 存放beandefinition对象
    ConcurrentHashMap<String,BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    //定义SingletonObjects 存放单例对象
    private ConcurrentHashMap<String,Object> singletonObject = new ConcurrentHashMap<>();

    //存放后置处理器
    private List<BeanPostProcessor> beanPostProcessorList=new ArrayList<>();

    public SpringApplicationContext(Class configClass) {
        //完成扫描
        beanDefinitionScan(configClass);
        System.out.println("beanDefinitionMap="+beanDefinitionMap);
        Enumeration<String> keys = beanDefinitionMap.keys();
        while (keys.hasMoreElements()) {
            String beanName = keys.nextElement();
            BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
            if("Singleton".equalsIgnoreCase(beanDefinition.getScope())) {
                Object bean = createBean(beanName,beanDefinition);
                singletonObject.put(beanName,bean);
            }
        }
        System.out.println("singletonObjects单例池="+singletonObject);
    }
    //指定包扫描 并将bean信息放在beandefintion对象在放入map中
    public void beanDefinitionScan(Class configClass) {
        this.configClass = configClass;

        ComponentScan componentScan = (ComponentScan) this.configClass.getDeclaredAnnotation(ComponentScan.class);
        String path = componentScan.value();
        System.out.println("扫描路径="+path);

        ClassLoader classLoader = SpringApplicationContext.class.getClassLoader();
        path = path.replace(".","/");
        URL resource = classLoader.getResource(path);
        File file = new File(resource.getFile());
        if(file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                String fileAbsolutePath = f.getAbsolutePath();
                if(fileAbsolutePath.endsWith(".class")) {
                    String className = fileAbsolutePath.substring(fileAbsolutePath.lastIndexOf("\\") + 1, fileAbsolutePath.indexOf(".class"));
                    String classFullPath = path.replace("/", ".") + "." + className;
                    try {
                        Class<?> clazz = classLoader.loadClass(classFullPath);
                        if(clazz.isAnnotationPresent(Component.class)) {
                            System.out.println("是一个bean="+clazz);

                            if(BeanPostProcessor.class.isAssignableFrom(clazz)) {
                                BeanPostProcessor beanPostProcessor = (BeanPostProcessor) clazz.newInstance();
                                beanPostProcessorList.add(beanPostProcessor);
                                continue;
                            }
                            //将bean信息封装到map
                            BeanDefinition beanDefinition = new BeanDefinition();
                            beanDefinition.setClazz(clazz);
                            Component componentAnnotation = clazz.getDeclaredAnnotation(Component.class);
                            String beanName = componentAnnotation.value();
                            if("".equals(beanName)) {
                                beanName = StringUtils.uncapitalize(className);
                            }
                            if(clazz.isAnnotationPresent(Scope.class)) {
                                Scope scopeAnnotation = clazz.getDeclaredAnnotation(Scope.class);
                                beanDefinition.setScope(scopeAnnotation.value());
                            }else  {
                                beanDefinition.setScope("singleton");
                            }
                            beanDefinitionMap.put(beanName,beanDefinition);
                        }else {
                            System.out.println("不是一个bean="+clazz);
                        }
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    } catch (InstantiationException e) {
                        throw new RuntimeException(e);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("=======================================");
                }
            }
        }
    }


    //create bean
    private Object createBean(String beanName,BeanDefinition beanDefinition) {
        Class clazz = beanDefinition.getClazz();
        try {
            Object instance = clazz.getDeclaredConstructor().newInstance();
            //添加依赖注入逻辑
            for (Field declaredField : clazz.getDeclaredFields()) {
                if(declaredField.isAnnotationPresent(Autowired.class)) {
                    Object bean = getBean(declaredField.getName());
                    declaredField.setAccessible(true);
                    declaredField.set(instance,bean);
                }
            }
            System.out.println("======创建好了===="+instance);

            //bean初始化前 调用处理器before
            for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
                Object current = beanPostProcessor.postProcessBeforeInitialization(instance,beanName);
                if(current!= null) {
                    instance = current;
                }
            }
            if(instance instanceof InitializingBean) {
                try {
                    ((InitializingBean) instance).afterPropertiesSet();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            //bean初始化后 调用处理器after
            for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
                Object current  = beanPostProcessor.postProcessAfterInitialization(instance,beanName);
                if(current!= null) {
                    instance =current;
                }
            }
            return instance;
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public Object getBean(String name) {
       if(beanDefinitionMap.containsKey(name)) {
           BeanDefinition beanDefinition = beanDefinitionMap.get(name);
           if("singleton".equalsIgnoreCase(beanDefinition.getScope())) {
               return singletonObject.get(name);
           }else {
               return createBean(name,beanDefinition);
           }
       }else {
           throw new NullPointerException("没有该bean");
       }
    }


}
