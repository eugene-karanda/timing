package biz.digitalhouse.tools.timing;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.client.RestTemplate;

/**
 * @author eugene.karanda
 * @version 1.0 Create: 03.08.2015 16:06
 */
@EnableAspectJAutoProxy
@SpringBootApplication
public class TimingWebFilterTest {

    @Bean
    TimingWebFilter timingWebFilter() {
        return new TimingWebFilter();
    }

    @Bean
    TestController testController() {
        return new TestController();
    }

    @Bean
    TimingAspect timingAspect() {
        return new TimingAspect();
    }

    @Test
    public void test() throws Exception {
        SpringApplication.run(TimingWebFilterTest.class);

        RestTemplate restTemplate = new RestTemplate();
        Integer response = restTemplate.getForObject("http://localhost:8080", Integer.class);
        Assert.assertEquals(Integer.valueOf(1), response);
    }
}
