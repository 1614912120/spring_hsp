package com.abc.Spring3;

public class Test {
    public static void main(String[] args) {
        Car car = new Car();
        VehicleProxyProvider vehicleProxyProvider = new VehicleProxyProvider(car);
        Vehicle proxy = vehicleProxyProvider.getProxy();
        System.out.println("proxy 编译类型是 Vehicle");
        System.out.println("proxy 运行类型" + proxy.getClass());
        proxy.run();
    }
}
