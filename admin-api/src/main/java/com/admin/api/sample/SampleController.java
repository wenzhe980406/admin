package com.admin.api.sample;

import com.admin.core.exception.BusinessException;
import com.admin.core.model.RemoteResponse;
import com.admin.client.sample.SampleClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 样例接口
 * @author 86
 * @date 2021/08/31 21:09
 */
@RestController
@RequestMapping("/sample")
public class SampleController {

    @Autowired
    private SampleClient sampleClient;

    /**
     * 样例接口
     * @author 86
     * @date 2021/08/31 21:09
     */
    @GetMapping("/hello")
    public String hello() {
        RemoteResponse<String> response = sampleClient.hello();
        return response.getData();
    }

}
