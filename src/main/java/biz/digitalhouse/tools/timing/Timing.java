package biz.digitalhouse.tools.timing;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author eugene.karanda
 * @version 1.0 Create: 02.08.2015 15:29
 */
public class Timing {
    private final Instant startInstant;
    private final Instant endInstant;
    private final Duration duration;
    private final String message;

    private final List<Timing> childTimings;

    private Timing(Instant startInstant, Instant endInstant, Duration duration, String message, List<Timing> childTimings) {
        this.startInstant = startInstant;
        this.endInstant = endInstant;
        this.duration = duration;
        this.message = message;
        this.childTimings = childTimings;
    }

    public static Timing create(Instant startInstant, Instant endInstant, String message, List<Timing> childTimings) {
        Objects.requireNonNull(startInstant, "'startInstant' must be not null");
        Objects.requireNonNull(endInstant, "'endInstant' must be not null");
        Objects.requireNonNull(message, "'message' must be not null");

        Duration duration = Duration.between(startInstant, endInstant);

        ArrayList<Timing> modifiableChildList = childTimings != null ?
                new ArrayList<Timing>(childTimings) :
                new ArrayList<Timing>();

        childTimings = Collections.unmodifiableList(modifiableChildList);
        return new Timing(startInstant, endInstant, duration, message, childTimings);
    }

    public Instant getStartInstant() {
        return startInstant;
    }

    public Instant getEndInstant() {
        return endInstant;
    }

    public Duration getDuration() {
        return duration;
    }

    public String getMessage() {
        return message;
    }

    public List<Timing> getChildTimings() {
        return childTimings;
    }

    @Override
    public String toString() { //TODO update
        return "Timing{" +
                "startInstant=" + startInstant +
                ", endInstant=" + endInstant +
                ", duration=" + duration +
                ", message='" + message + '\'' +
                ", childTimings=" + childTimings +
                '}';
    }
}
