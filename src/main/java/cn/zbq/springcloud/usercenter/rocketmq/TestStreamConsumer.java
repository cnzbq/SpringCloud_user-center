package cn.zbq.springcloud.usercenter.rocketmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Service;

/**
 * spring cloud stream consumer test
 *
 * @author Zbq
 * @since 2019/11/24 18:13
 */
@Service
@Slf4j
public class TestStreamConsumer {

    @StreamListener(Sink.INPUT)
    public void receive(String messageBody) {
        log.info("通过stream 收到了消息，messageBody={}", messageBody);
    }
}
