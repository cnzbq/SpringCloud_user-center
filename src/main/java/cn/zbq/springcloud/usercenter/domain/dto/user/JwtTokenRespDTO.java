package cn.zbq.springcloud.usercenter.domain.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * token
 *
 * @author Zbq
 * @since 2019/12/21 10:30
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtTokenRespDTO implements Serializable {
    /**
     * token
     */
    private String token;
    /**
     * 过期时间
     */
    private Long expirationTime;
}
