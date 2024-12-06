package abc.test;

import abc.annotation.component.MonsterService;
import abc.annotation.component.SmartAnimalable;
import abc.annotation.config.SpringConfig;
import abc.annotation.ioc.SpringApplicationContext;

public class Main {
    public static void main(String[] args) {
        SpringApplicationContext ioc = new SpringApplicationContext(SpringConfig.class);
//        System.out.println(ioc.getBean("monsterService"));
//        System.out.println(ioc.getBean("monsterService"));
//        System.out.println(ioc.getBean("monsterService"));

//        MonsterService monsterService = (MonsterService) ioc.getBean("monsterService");
//        monsterService.m1();

        SmartAnimalable smartDog =
                (SmartAnimalable)
                        ioc.getBean("smartDog");
        smartDog.getSum(10, 20);
    }
}
