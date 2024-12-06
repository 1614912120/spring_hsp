package com.abc.Spring3;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class VehicleProxyProvider {
    private Vehicle target_vehicle;

    public VehicleProxyProvider(Vehicle target_vehicle) {
        this.target_vehicle = target_vehicle;
    }

    public Vehicle getProxy() {
        ClassLoader classLoader = target_vehicle.getClass().getClassLoader();
        Class<?>[] interfaces = target_vehicle.getClass().getInterfaces();
        InvocationHandler invocationHandler = new InvocationHandler(){

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("start---------");
                Object invoke = method.invoke(target_vehicle, args);
                System.out.println("end---------");
                return invoke;
            }
        };
        Vehicle proxy = (Vehicle)Proxy.newProxyInstance(classLoader,interfaces,invocationHandler);
        return proxy;
    }
}
