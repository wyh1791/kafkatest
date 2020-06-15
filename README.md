# kafkatest
#### kafka 测试工程

### 使用说明
##### 1.修改kafka地址配置
application.yml文件下
```
    bootstrap-servers: 192.168.2.104:9092 #kafka地址
```

##### 2.启动工程
运行单元测试KafkaProducerTest
```
kafkatest.kafka.KafkaProducerTest.sendMessage()
```
