package cn.zbq.springcloud.usercenter.rocketmq;

import cn.zbq.springcloud.usercenter.dao.user.BonusEventLogMapper;
import cn.zbq.springcloud.usercenter.dao.user.UserMapper;
import cn.zbq.springcloud.usercenter.domain.dto.message.UserAddBonusMagDTO;
import cn.zbq.springcloud.usercenter.domain.entity.user.BonusEventLog;
import cn.zbq.springcloud.usercenter.domain.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 增加积分监听
 *
 * @author Zbq
 * @since 2019/11/23 11:16
 */
@Slf4j
@Service
@RocketMQMessageListener(consumerGroup = "consumer-group", topic = "add-bonus")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AddBonusListener implements RocketMQListener<UserAddBonusMagDTO> {

    private final UserMapper userMapper;
    private final BonusEventLogMapper bonusEventLogMapper;

    /**
     * 当收到消息以后执行的业务
     */
    @Override
    public void onMessage(UserAddBonusMagDTO userAddBonusMagDTO) {
        // 1. 为用户增加积分
        Integer userId = userAddBonusMagDTO.getUserId();
        User user = this.userMapper.selectByPrimaryKey(userId);
        Integer bonus = userAddBonusMagDTO.getBonus();
        user.setBonus(user.getBonus() + bonus);
        this.userMapper.updateByPrimaryKey(user);

        // 2. 记录日志到bonus_event_log表里面
        this.bonusEventLogMapper.insert(
                BonusEventLog.builder()
                        .userId(userId)
                        .value(bonus)
                        .createTime(new Date())
                        .event("CONTRIBUTE")
                        .description("投稿加积分...")
                        .build()
        );
        log.info("积分添加完毕...");
    }
}
