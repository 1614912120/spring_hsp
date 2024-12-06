package com.abc.proxy1;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class SmartAnimalAspect {

    @Before(value = "execution(public float com.abc.proxy1.SmartDog.getSum(float , float ))")
    public void showBeginLog(JoinPoint joinPoint){
        System.out.println("前置通知");
        Signature signature = joinPoint.getSignature();
        Object target = joinPoint.getTarget();
        System.out.println(target.getClass());
        Object aThis = joinPoint.getThis();
        System.out.println(aThis.getClass());
        System.out.println(signature.getName()+"方法参数"+ Arrays.asList(joinPoint.getArgs()));
    }
}
