package cn.zbq.springcloud.usercenter.service.user;

import cn.zbq.springcloud.usercenter.dao.user.BonusEventLogMapper;
import cn.zbq.springcloud.usercenter.dao.user.UserMapper;
import cn.zbq.springcloud.usercenter.domain.dto.message.UserAddBonusMsgDTO;
import cn.zbq.springcloud.usercenter.domain.dto.user.UserLoginDTO;
import cn.zbq.springcloud.usercenter.domain.entity.user.BonusEventLog;
import cn.zbq.springcloud.usercenter.domain.entity.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 用户服务
 *
 * @author Zbq
 * @since 2019/9/7 23:30
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {
    // 方法一：
//    @Autowired
//    private UserMapper userMapper;
    private final UserMapper userMapper;
    private final BonusEventLogMapper bonusEventLogMapper;

    /**
     * 通过id查询用户
     *
     * @param id 用户id
     * @return user
     */
    public User userFindById(Integer id) {
        // select * from user where id = #{id}
        return this.userMapper.selectByPrimaryKey(id);
    }

    @Transactional(rollbackFor = Exception.class)
    public void addBonus(UserAddBonusMsgDTO userAddBonusMsgDTO) {
        // 1. 为用户增加积分
        Integer userId = userAddBonusMsgDTO.getUserId();
        User user = this.userMapper.selectByPrimaryKey(userId);
        Integer bonus = userAddBonusMsgDTO.getBonus();
        user.setBonus(user.getBonus() + bonus);
        this.userMapper.updateByPrimaryKey(user);

        // 2. 记录日志到bonus_event_log表里面
        this.bonusEventLogMapper.insert(
                BonusEventLog.builder()
                        .userId(userId)
                        .value(bonus)
                        .createTime(new Date())
                        .event(userAddBonusMsgDTO.getEvent())
                        .description(userAddBonusMsgDTO.getDescription())
                        .build()
        );
        log.info("积分添加完毕...");
    }

    public User login(UserLoginDTO userLoginDTO, String openId) {
        User user = this.userMapper.selectOne(
                User.builder()
                        .wxId(openId)
                        .build()
        );

        if (user == null) {
            User userToSave = User.builder()
                    .wxId(openId)
                    .bonus(300)
                    .wxNickname(userLoginDTO.getWxNickname())
                    .avatarUrl(userLoginDTO.getAvatarUrl())
                    .roles("user")
                    .createTime(new Date())
                    .updateTime(new Date())
                    .build();
            this.userMapper.insertSelective(
                    userToSave
            );
            return userToSave;
        }
        return user;
    }
}
