package com.admin.api.system;

import com.admin.client.system.UserClient;
import com.admin.core.model.RemoteResponse;
import com.admin.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author changyw
 * @date 2021/9/2
 */
@RestController
public class UserController {

    @Autowired
    private UserClient userClient;

    /**
     * 获取所有用户列表
     * @date 2021-09-02 10:00
     * @return 1
     */
    @GetMapping("/user/list")
    public RemoteResponse<List<User>> list() {
        return userClient.list();
    }
}
