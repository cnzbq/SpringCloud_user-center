package cn.zbq.springcloud.usercenter.controller.user;

import cn.zbq.springcloud.usercenter.domain.entity.user.User;
import cn.zbq.springcloud.usercenter.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户控制器
 *
 * @author Zbq
 * @since 2019/9/7 23:38
 */
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 通过id查询用户
     * @param id 用户id
     * @return user
     */
    @RequestMapping("/{id}")
    public User findUserById(@PathVariable Integer id) {
        return this.userService.userFindById(id);
    }
}
