package abc.annotation.component;

import abc.annotation.annotation.Component;

@Component("smartDog")
public class SmartDog implements SmartAnimalable{
    @Override
    public float getSum(float i, float j) {
        float result = i + j;
        System.out.println("getSum() 方法内部打印 result= " + result);
        return result;
    }

    @Override
    public float getSub(float i, float j) {
        float result = i - j;
        System.out.println("getSub() 方法内部打印 result= " + result);
        return result;
    }
}
