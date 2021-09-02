package com.admin.client.sample;

import com.admin.core.model.RemoteResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 样例服务客户端
 * @author 86
 * @date 2021/08/31 21:09
 */
//@FeignClient("SAMPLE-SERVICE")
public interface SampleClient {

    /**
     * 服务样例接口
     * @author 86
     * @date 2021/08/31 21:09
     */
    @GetMapping("/sample/hello")
    RemoteResponse<String> hello();

}
