package kafkatest.kafka;


import com.clubfactory.center.common.util.BaseUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.MDC;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

/**
 * @author yehuizhang
 * @create 2018-09-18 13:25
 */
@Slf4j
@Component
public class KafkaConsumer {


    @KafkaListener(topics = "#{'${spring.topics.topticTest.topic}'}", groupId = "#{'${spring.topics.topticTest.group}'}")
    public void processMessage(ConsumerRecord<String, String> record, Acknowledgment acknowledgment) {
        try {
            String key = record.key();
            String data = record.value();

            log.info("kafka receive message, key {}, data {}", key, data);
            if (BaseUtil.isNotEmpty(data)) {
//                System.out.println(data);
                acknowledgment.acknowledge();
            }else{
                acknowledgment.acknowledge();
            }
        } catch (Exception e) {
            String suuid = MDC.get("UUID") == null ? "" : MDC.get("UUID");
            log.error("消费消息异常，请线上查找原因: {}", suuid, e);
            String message = "kafka同步消费商品信息异常:" + suuid;
        }
    }


}
