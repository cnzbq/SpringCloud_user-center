package cn.zbq.springcloud.usercenter.rocketmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

/**
 * spring cloud stream consumer test for 自定义接口
 *
 * @author Zbq
 * @since 2019/11/24 18:13
 */
@Service
@Slf4j
public class MyTestStreamConsumer {

    @StreamListener(MySink.MY_INPUT)
    public void receive(String messageBody) {
        log.info("自定义接口消费消息->通过stream 收到了消息，messageBody={}", messageBody);
    }
}
