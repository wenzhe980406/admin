package com.admin.service.sample.api;

import com.admin.core.model.RemoteResponse;
import com.admin.client.sample.SampleClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 服务样例
 * @author 86
 * @date 2021/08/31 21:09
 */
@RestController
@RequestMapping("/sample")
public class SampleController implements SampleClient {

    @GetMapping("/hello")
    @Override
    public RemoteResponse<String> hello() {
        return RemoteResponse.success("Hello Spring Cloud");
    }

}
