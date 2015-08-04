package biz.digitalhouse.tools.timing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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

    @Autowired
    private Foo foo;

    @Measured
    @RequestMapping(method = RequestMethod.GET)
    public int one() {
        String str1 = foo.toString(3);
        String str2 = foo.toString(4);
        Bar.three();

        return 1;
    }
}

@Component
class Foo {
    @Measured
    public String toString(int value) {
        return Integer.toString(value);
    }
}

class Bar {
    @Measured
    public static boolean three() {
        return false;
    }
}