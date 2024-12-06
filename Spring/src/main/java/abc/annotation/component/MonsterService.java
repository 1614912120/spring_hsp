package abc.annotation.component;

import abc.annotation.annotation.Autowired;
import abc.annotation.annotation.Component;
import abc.annotation.annotation.Scope;
import abc.annotation.processe.InitializingBean;

@Component("monsterService")
@Scope("prototype")
public class MonsterService  implements InitializingBean {

    @Autowired
    private MonsterDao monsterDao;

    public void m1() {
        monsterDao.hi();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("set执行之后 --初始化方法执行成功");
    }

}
