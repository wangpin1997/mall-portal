package cn.wpin.mall.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author wangpin
 */
@SpringBootApplication(scanBasePackages = "cn.wpin.mall")
@EnableDiscoveryClient
@EnableFeignClients("cn.wpin.mall.client")
public class PortalApplication {

    public static void main(String[] args) {
        SpringApplication.run(PortalApplication.class, args);
    }
}
