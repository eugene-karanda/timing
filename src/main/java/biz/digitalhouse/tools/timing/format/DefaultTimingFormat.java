package biz.digitalhouse.tools.timing.format;

import biz.digitalhouse.tools.timing.Timing;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * @author eugene.karanda
 * @version 1.0 Create: 03.08.2015 17:22
 */
public class DefaultTimingFormat implements TimingFormat {

    private DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss.SSS");

    @Override
    public String format(Timing timing) {
        return format(new StringBuilder(), timing, 0)
                .toString();
    }

    private StringBuilder format(StringBuilder builder, Timing timing, int level) {
        String tabs = tabs(level);

        builder.append(tabs)
                .append("'")
                .append(timing.getMessage())
                .append("' : ")
                .append(timing.getDuration())
                .append(" ms, ")
                .append(dateFormat.format(timing.getStartInstant()))
                .append(" {")
                .append("\n");

        for (Timing child : timing.getChildTimings()) {
            format(builder, child, level + 1);
        }

        builder.append(tabs)
                .append("}, ")
                .append("end = ")
                .append(dateFormat.format(timing.getEndInstant()))
                .append("\n");

        return builder;
    }

    private String tabs(int level) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < level; i++) {
            builder.append("\t");
        }

        return builder.toString();
    }
}
