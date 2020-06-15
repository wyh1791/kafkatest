package kafkatest.kafka;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by xiaojiang.lxj on 2018-05-30 11:11.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BaseTest implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Test
    public void showApplicationContext(){
        if(applicationContext != null){
            System.out.println("applicationContext : "+ applicationContext);
        }else{
            System.out.println("applicationContext is null, please check whether it is initialized or not.");
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
