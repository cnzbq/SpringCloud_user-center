package cn.zbq.springcloud.usercenter.rocketmq;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * spring cloud stream 自定义接口 消费消息
 *
 * @author Zbq
 * @since 2019/11/24 21:54
 */
public interface MySink {

    String MY_INPUT = "my-input";

    @Input(MY_INPUT)
    SubscribableChannel input();
}
