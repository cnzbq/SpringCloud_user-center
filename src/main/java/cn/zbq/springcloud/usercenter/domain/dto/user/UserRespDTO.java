package cn.zbq.springcloud.usercenter.domain.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * user dto
 *
 * @author Zbq
 * @since 2019/12/21 14:29
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRespDTO implements Serializable {
    /**
     * 头像地址
     */
    private String avatarUrl;
    /**
     * 积分
     */
    private Integer bonus;
    /**
     * id
     */
    private Integer id;
    /**
     * 微信昵称
     */
    private String wxNickname;
}
