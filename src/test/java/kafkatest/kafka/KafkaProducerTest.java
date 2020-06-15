package kafkatest.kafka;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * description:
 *
 * @author wyh
 * @date 2018/12/18 21:58
 */
public class KafkaProducerTest extends BaseTest {
    @Autowired
    KafkaProducer kafkaProducer;

    @Test
    public void sendMessage() throws Exception {
        Integer seq = 0;
        while (true) {
            kafkaProducer.sendMessage("test", "this is kafka test message! seq:" + seq++);
            Thread.sleep(1000);
        }
    }

}
