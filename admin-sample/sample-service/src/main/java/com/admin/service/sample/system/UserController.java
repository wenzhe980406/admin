package com.admin.service.sample.system;

import com.admin.client.system.UserClient;
import com.admin.core.model.RemoteResponse;
import com.admin.pojo.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author changyw
 * @date 2021/9/2
 */
@RestController
@RequestMapping("/user")
public class UserController implements UserClient {

    /**
     * 获取所有用户列表
     * @date 2021-09-02 10:00
     * @return 1
     */
    @Override
    @GetMapping("/list")
    public RemoteResponse<List<User>> list() {
        ArrayList<User> users = new ArrayList<>();
        User user = new User();
        user.setMobile("13838384388");
        users.add(user);
        return RemoteResponse.success(users);
    }

}
