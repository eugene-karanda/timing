package biz.digitalhouse.tools.timing;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author eugene.karanda
 * @version 1.0 Create: 03.08.2015 16:28
 */
@RestController
@RequestMapping("/")
public class TestController {

    @Measured
    @RequestMapping(method = RequestMethod.GET)
    public int one() {
        return 1;
    }
}
