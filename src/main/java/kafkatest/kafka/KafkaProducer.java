package kafkatest.kafka;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.UUID;

/**
 * @author yehuizhang
 * @create 2018-09-18 10:42
 */
@Slf4j
@Component
public class KafkaProducer {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @Value("${spring.topics.topticTest.topic}")
    private String topic;

    /**
     * 相同的key发到同一个partition
     * <p>
     * 由于kafka是根据key的hash值取模去分的partition 导致肯能分布不均，所以此处随机去partition的值
     * @param key
     * @param data
     * @param <T>
     * @return
     */
    public <T> boolean sendMessage(String key, T data) {
        String jsonData = JSONObject.toJSONString(data);
        UUID uuid = UUID.randomUUID();
        String suuid = StringUtils.remove(uuid.toString(), "-");
        try {
            int partitionSize = kafkaTemplate.partitionsFor(topic).size();
            int randomPartition = (int) (System.currentTimeMillis() % partitionSize);
            Header header = new RecordHeader("UUID", suuid.getBytes());
            ProducerRecord producerRecord = new ProducerRecord(topic, randomPartition, key, jsonData, Arrays.asList(header));
//            log.info("begin send key {}, data {}, uuid {}", key, data, suuid);
            kafkaTemplate.send(producerRecord);
//            log.info("after send uuid {}", suuid);
            return true;
        } catch (Exception e) {
            log.error("sendMessage error, suuid {}, key {}, data {}", suuid, key, jsonData, e);
            String message = "商品同步kafka消息发送失败:" + suuid;
            return false;
        }
    }

}
