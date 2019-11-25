package cn.zbq.springcloud.usercenter.rocketmq;

import cn.zbq.springcloud.usercenter.domain.dto.message.UserAddBonusMsgDTO;
import cn.zbq.springcloud.usercenter.service.user.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Service;

/**
 * spring cloud stream consumer test
 *
 * @author Zbq
 * @since 2019/11/24 18:13
 */
@Slf4j
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class AddBonusStreamConsumer {

    private final UserService userService;

    @StreamListener(Sink.INPUT)
    public void receive(UserAddBonusMsgDTO dto) {
        userService.addBonus(dto);
    }
}
