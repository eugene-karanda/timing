package biz.digitalhouse.tools.timing;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author eugene.karanda
 * @version 1.0 Create: 03.08.2015 13:57
 */
public class TimingBuilderTest {
    @Test
    public void test1() throws Exception {
        TimingBuilder builder = new TimingBuilder();
        Timing timing = builder.start("method")
                .end()
                .build();

        System.out.println(timing);
    }

    @Test
    public void test2() throws Exception {
        TimingBuilder builder = new TimingBuilder();
        Timing timing = builder.start("outer method")
                .start("inner method")
                .end()
                .end()
                .build();

        System.out.println(timing);
    }

    @Test
    public void test3() throws Exception {
        TimingBuilder builder = new TimingBuilder();
        Timing timing = builder.start("parent method")
                .start("method 1")
                .end()
                .start("method 2")
                .end()
                .end()
                .build();

        System.out.println(timing);
    }
}