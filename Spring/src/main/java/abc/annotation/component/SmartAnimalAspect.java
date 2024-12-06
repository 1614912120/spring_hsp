package abc.annotation.component;


public class SmartAnimalAspect {
    public static void showBeginLog() {
        System.out.println("前置通知");
    }
    public static void showSuccessEndLog() {
        System.out.println("返回通知");
    }
}
