package cn.zbq.springcloud.usercenter.domain.dto.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户增加积分消息dto
 *
 * @author Zbq
 * @since 2019/11/22 21:53
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAddBonusMsgDTO implements Serializable {

    private static final long serialVersionUID = -7755414356123528392L;

    /**
     * 为谁增加积分
     */
    private Integer userId;
    /**
     * 加多少积分
     */
    private Integer bonus;
    /**
     * 发生的事件
     */
    private String event;
    /**
     * 描述
     */
    private String description;
}
