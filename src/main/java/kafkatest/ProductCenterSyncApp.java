package kafkatest;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("kafkatest")
public class ProductCenterSyncApp {
    public static void main(String[] args) {
        SpringApplication.run(ProductCenterSyncApp.class, args);
    }
}
