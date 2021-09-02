package com.admin.client.system;

import com.admin.core.model.RemoteResponse;
import com.admin.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author changyw
 * @date 2021/9/2
 */

@FeignClient("SAMPLE-SERVICE")
public interface UserClient {

    /**
     * 获取所有用户列表
     * @date 2021-09-02 10:00
     * @return 1
     */
    @GetMapping("/user/list")
    RemoteResponse<List<User>> list();

}
