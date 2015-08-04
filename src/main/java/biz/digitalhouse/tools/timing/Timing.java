package biz.digitalhouse.tools.timing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author eugene.karanda
 * @version 1.0 Create: 02.08.2015 15:29
 */
public class Timing {
    private final Date startInstant;
    private final Date endInstant;
    private final long duration;
    private final String message;

    private final List<Timing> childTimings;

    private Timing(Date startInstant, Date endInstant, long duration, String message, List<Timing> childTimings) {
        this.startInstant = startInstant;
        this.endInstant = endInstant;
        this.duration = duration;
        this.message = message;
        this.childTimings = childTimings;
    }

    public static Timing create(Date startInstant, Date endInstant, String message, List<Timing> childTimings) {
        Objects.requireNonNull(startInstant, "'startInstant' must be not null");
        Objects.requireNonNull(endInstant, "'endInstant' must be not null");
        Objects.requireNonNull(message, "'message' must be not null");

        long duration = endInstant.getTime() - startInstant.getTime();

        ArrayList<Timing> modifiableChildList = childTimings != null ?
                new ArrayList<Timing>(childTimings) :
                new ArrayList<Timing>();

        childTimings = Collections.unmodifiableList(modifiableChildList);
        return new Timing(startInstant, endInstant, duration, message, childTimings);
    }

    public Date getStartInstant() {
        return startInstant;
    }

    public Date getEndInstant() {
        return endInstant;
    }

    public long getDuration() {
        return duration;
    }

    public String getMessage() {
        return message;
    }

    public List<Timing> getChildTimings() {
        return childTimings;
    }

    @Override
    public String toString() {
        return "Timing{" +
                "startInstant=" + startInstant +
                ", endInstant=" + endInstant +
                ", duration=" + duration +
                ", message='" + message + '\'' +
                ", childTimings=" + childTimings +
                '}';
    }
}
