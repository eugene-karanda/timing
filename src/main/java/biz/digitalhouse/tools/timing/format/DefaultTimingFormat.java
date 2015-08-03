package biz.digitalhouse.tools.timing.format;

import biz.digitalhouse.tools.timing.Timing;

/**
 * @author eugene.karanda
 * @version 1.0 Create: 03.08.2015 17:22
 */
public class DefaultTimingFormat implements TimingFormat {
    @Override
    public String format(Timing timing) {
        StringBuilder builder = new StringBuilder();

        builder.append(timing.getMessage());
        builder.append(": ");
        builder.append(timing.getStartInstant());
        builder.append(" - ");
        builder.append(timing.getEndInstant());
        builder.append(" = ");
        builder.append(timing.getDuration());

        return builder.toString();
    }
}
