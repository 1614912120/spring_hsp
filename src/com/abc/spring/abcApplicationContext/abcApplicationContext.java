package com.abc.spring.abcApplicationContext;

import com.abc.spring.bean.Master;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

//实现spring简单容器机制
public class abcApplicationContext {
   private ConcurrentHashMap<String,Object> ioc = new ConcurrentHashMap<>();

    //接收容器配置文件
    public abcApplicationContext(String iocBeanXmlFile) throws DocumentException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        String path = this.getClass().getResource("/").getPath();
        System.out.println("类路径"+path);

        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(new File(path + iocBeanXmlFile));
        Element rootElement = document.getRootElement();
        Element bean = (Element) rootElement.elements("bean").get(0);
        String id = bean.attributeValue("id");
        String aClass = bean.attributeValue("class");
        List<Element> property = bean.elements("property");
        Integer monster = Integer.parseInt(property.get(0).attributeValue("value"));
        String name = property.get(1).attributeValue("value");
        String skill = property.get(2).attributeValue("value");

        //使用反射创建bean实例 并放入到ioc中
        Class<?> aClass1 = Class.forName(aClass);
        Master instance = (Master)aClass1.newInstance();
        instance.setMasterId(monster);
        instance.setName(name);
        instance.setSkill(skill);

        ioc.put(id,instance);
    }

    public Object getBean(String id){
        return ioc.get(id);
    }
}
