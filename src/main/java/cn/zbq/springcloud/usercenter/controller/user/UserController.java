package cn.zbq.springcloud.usercenter.controller.user;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.zbq.springcloud.usercenter.auth.LoginCheck;
import cn.zbq.springcloud.usercenter.domain.dto.user.JwtTokenRespDTO;
import cn.zbq.springcloud.usercenter.domain.dto.user.LoginRespDTO;
import cn.zbq.springcloud.usercenter.domain.dto.user.UserLoginDTO;
import cn.zbq.springcloud.usercenter.domain.dto.user.UserRespDTO;
import cn.zbq.springcloud.usercenter.domain.entity.user.User;
import cn.zbq.springcloud.usercenter.service.user.UserService;
import cn.zbq.springcloud.usercenter.util.JwtOperator;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * 用户控制器
 *
 * @author Zbq
 * @since 2019/9/7 23:38
 */
@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private WxMaService wxMaService;
    @Autowired
    private JwtOperator jwtOperator;

    /**
     * 通过id查询用户
     *
     * @param id 用户id
     * @return user
     */
    @LoginCheck
    @GetMapping("/{id}")
    public User findUserById(@PathVariable Integer id) {
        log.info("我被请求了");
        return this.userService.userFindById(id);
    }

    @PostMapping("/login")
    public LoginRespDTO login(@RequestBody UserLoginDTO userLoginDTO) throws WxErrorException {
        // 微信小程序服务端校验小程序是否登录
        WxMaJscode2SessionResult result = this.wxMaService.getUserService()
                .getSessionInfo(userLoginDTO.getCode());
        // 微信的openId,用户在微信的唯一标识
        String openid = result.getOpenid();

        // 判断用户是否已经注册，没有注册插入用户信息，已经注册颁发token
        User user = this.userService.login(userLoginDTO, openid);
        HashMap<String, Object> userInfo = new HashMap<>(3);
        userInfo.put("id", user.getId());
        userInfo.put("wxNickname", user.getWxNickname());
        userInfo.put("role", user.getRoles());
        String token = jwtOperator.generateToken(userInfo);
        log.info("用户{}登录成功，生成的token={},有效期到:{}}",
                userLoginDTO.getWxNickname(),
                token,
                jwtOperator.getExpirationTime()
        );

        // 构建响应
        return LoginRespDTO.builder()
                .user(
                        UserRespDTO.builder()
                                .id(user.getId())
                                .avatarUrl(user.getAvatarUrl())
                                .bonus(user.getBonus())
                                .wxNickname(user.getWxNickname())
                                .build())
                .token(
                        JwtTokenRespDTO.builder()
                                .expirationTime(jwtOperator.getExpirationTime().getTime())
                                .token(token)
                                .build())
                .build();
    }

    /**
     * 模拟生成token
     */
    @GetMapping("gen-token")
    public String genToken() {
        HashMap<String, Object> userInfo = new HashMap<>(3);
        userInfo.put("id", 1);
        userInfo.put("wxNickname", "快乐每一天");
        userInfo.put("role", "user");
        return this.jwtOperator.generateToken(userInfo);
    }
}
