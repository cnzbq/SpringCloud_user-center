package cn.zbq.springcloud.usercenter;

import cn.zbq.springcloud.usercenter.dao.user.UserMapper;
import cn.zbq.springcloud.usercenter.domain.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * TODO
 *
 * @author Dingwq
 * @since 2019/9/4 22:38
 */
@RestController
public class TestController {
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/test")
    public User testInsert() {
        User user = new User();
        user.setAvatarUrl("xxx");
        user.setBonus(100);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        // insert()  insert into (所有字段) values ()
        // insertSelective  insert into (不为null的字段) values ()
        this.userMapper.insertSelective(user);
        return user;
    }


    /**
     * q?id=xx&wxId=xx&wxNickname=xx...
     * @param user
     * @return
     */
    @GetMapping("/q")
    public User query(User user) {
        return user;
    }
}
