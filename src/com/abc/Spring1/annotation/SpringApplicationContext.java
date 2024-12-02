package com.abc.Spring1.annotation;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.io.File;
import java.net.URL;
import java.util.concurrent.ConcurrentHashMap;

public class SpringApplicationContext {
    private Class configClass;
    private final ConcurrentHashMap<String,Object> ioc = new ConcurrentHashMap<>();
    //通过反射创建的对象
    public ConcurrentHashMap<String,Object> getIoc() {
        return ioc;
    }

    public SpringApplicationContext(Class configClass) throws ClassNotFoundException {
        this.configClass = configClass;
        ComponentScan componentScan = (ComponentScan) this.configClass.getDeclaredAnnotation(ComponentScan.class);
        String path = componentScan.value();
        System.out.println("scan path"+path);
        //classloader
        ClassLoader classLoader = SpringApplicationContext.class.getClassLoader();
        path = path.replace(".","/");
        URL resource = classLoader.getResource(path);
        System.out.println("resource"+resource);
        File file = new File(resource.getFile());
        if(file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                String fileAbsolutePath = f.getAbsolutePath();
                System.out.println("-----------------------------");
                System.out.println("file absolute path"+fileAbsolutePath);
                if(fileAbsolutePath.endsWith(".class")) {
                    String className = fileAbsolutePath.substring(fileAbsolutePath.lastIndexOf("\\")+1,fileAbsolutePath.indexOf(".class"));
                    String classFullPath = path.replace("/",".")+"."+className;
                    System.out.println("className="+className);
                    System.out.println("class full path"+classFullPath);

                    Class<?> clazz = classLoader.loadClass(classFullPath);
                    if(clazz.isAnnotationPresent(Component.class)||
                        clazz.isAnnotationPresent(Controller.class)||
                        clazz.isAnnotationPresent(Service.class)||
                        clazz.isAnnotationPresent(Repository.class)) {
                        System.out.println("is bean"+clazz);
                        Class<?> aClass = Class.forName(classFullPath);
                        if(aClass.isAnnotationPresent(Component.class)) {
                            Component declaredAnnotation = aClass.getDeclaredAnnotation(Component.class);
                            String value = declaredAnnotation.value();
                            if(!"".endsWith(value)) {
                                className = value;
                            }
                        }
                        try {
                            Object instance = aClass.newInstance();
                            ioc.put(className,instance);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }

                    }else{
                        System.out.println("is not bean="+clazz);
                    }
                }
                System.out.println("********************************");
            }
        }
    }

    public Object getBean(String name) {
        return ioc.get(name);
    }
}
