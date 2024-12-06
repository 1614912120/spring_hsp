package abc.annotation.component;

import abc.annotation.annotation.Component;

@Component("monsterDao")
public class MonsterDao {
    public void hi() {
        System.out.println("hi 我是monster dao");
    }
}
