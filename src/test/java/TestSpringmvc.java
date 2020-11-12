
import fhq.pojo.User;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class TestSpringmvc {
    @Test
    public void t1(){
        //加载核心配置文件
        ClassPathXmlApplicationContext ca=new ClassPathXmlApplicationContext("spring/spring.xml");
        //获取对象
        User user = ca.getBean("user", User.class);
        System.out.println(user);
    }
}
