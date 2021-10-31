package codingzx.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author ziya
 * @date 2021/2/20 3:58 下午
 */
@RestController
// 实时刷新配置文件
@RefreshScope
public class ConfigController {

    @Value("${testStr}")
    private String testStr;

    @Value("${mysql}")
    private String mysqlStr;

    @Value("${redis}")
    private String redisStr;


    @GetMapping("/getstr")
    public String getTestStr() {
        return testStr + "  " + mysqlStr + "  " + redisStr;
    }


}
