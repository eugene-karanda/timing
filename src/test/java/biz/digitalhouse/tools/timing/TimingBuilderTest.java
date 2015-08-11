package biz.digitalhouse.tools.timing;

import org.junit.Test;

/**
 * @author eugene.karanda
 * @version 1.0 Create: 03.08.2015 13:57
 */
public class TimingBuilderTest {
    @Test
    public void test1() throws Exception {
        TimingBuilder builder = new TimingBuilder();
        TimingBuilder.Token token = builder.start("token");
        builder.end(token);
        Timing timing = builder.build();

        System.out.println(timing);
    }

    @Test
    public void test2() throws Exception {
        TimingBuilder builder = new TimingBuilder();
        TimingBuilder.Token outerToken = builder.start("outer method");
        TimingBuilder.Token innerToken = builder.start("inner method");
        builder.end(innerToken);
        builder.end(outerToken);
        Timing timing = builder.build();

        System.out.println(timing);
    }

    @Test
    public void test3() throws Exception {
        TimingBuilder builder = new TimingBuilder();
        TimingBuilder.Token parentToken = builder.start("parent method");
        TimingBuilder.Token method1Token = builder.start("method 1");
        builder.end(method1Token);
        TimingBuilder.Token method2Token = builder.start("method 2");
        builder.end(method2Token);
        builder.end(parentToken);
        Timing timing = builder.build();

        System.out.println(timing);
    }
}