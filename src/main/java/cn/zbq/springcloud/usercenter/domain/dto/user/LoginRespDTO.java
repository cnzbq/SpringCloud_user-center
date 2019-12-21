package cn.zbq.springcloud.usercenter.domain.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 登录响应DTO
 *
 * @author Zbq
 * @since 2019/12/21 14:33
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginRespDTO implements Serializable {
    /**
     * token信息
     */
    private JwtTokenRespDTO token;
    /**
     * 用户信息
     */
    private UserRespDTO user;
}
