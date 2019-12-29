package cn.zbq.springcloud.usercenter.controller.user;

import cn.zbq.springcloud.usercenter.domain.dto.message.UserAddBonusMsgDTO;
import cn.zbq.springcloud.usercenter.domain.dto.user.UserAddBonusDTO;
import cn.zbq.springcloud.usercenter.domain.entity.user.User;
import cn.zbq.springcloud.usercenter.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 积分控制器
 *
 * @author Zbq
 * @since 2019/12/29 13:36
 */
@RestController
@RequestMapping("/users")
public class BonusController {

    @Autowired
    private UserService userService;

    @PutMapping("/add-bonus")
    public User addBonus(@RequestBody UserAddBonusDTO userAddBonusDTO) {
        Integer userId = userAddBonusDTO.getUserId();
        userService.addBonus(UserAddBonusMsgDTO.builder()
                .userId(userId)
                .description("兑换分享")
                .event("BUY")
                .bonus(userAddBonusDTO.getBonus())
                .build());
        return this.userService.userFindById(userId);
    }
}
