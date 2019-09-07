package cn.zbq.springcloud.usercenter.service.user;

import cn.zbq.springcloud.usercenter.dao.user.UserMapper;
import cn.zbq.springcloud.usercenter.domain.entity.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户服务
 *
 * @author Zbq
 * @since 2019/9/7 23:30
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {
    // 方法一：
//    @Autowired
//    private UserMapper userMapper;
    private final UserMapper userMapper;

    /**
     * 通过id查询用户
     * @param id 用户id
     * @return user
     */
    public User userFindById(Integer id){
        // select * from user where id = #{id}
        return this.userMapper.selectByPrimaryKey(id);
    }

}
