package cn.zbq.springcloud.usercenter.domain.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户登录信息
 *
 * @author Zbq
 * @since 2019/12/21 14:36
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginDTO implements Serializable {
    /**
     * 头像地址
     */
    private String avatarUrl;
    /**
     * code
     */
    private String code;
    /**
     * 微信昵称
     */
    private String wxNickname;
}
