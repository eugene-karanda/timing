package biz.digitalhouse.tools.timing.format;

import biz.digitalhouse.tools.timing.Timing;

/**
 * @author eugene.karanda
 * @version 1.0 Create: 03.08.2015 17:29
 */
public class DebuggingTimingFormat implements TimingFormat {
    @Override
    public String format(Timing timing) {
        return timing.toString();
    }
}
