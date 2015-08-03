package biz.digitalhouse.tools.timing;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author eugene.karanda
 * @version 1.0 Create: 02.08.2015 16:36
 */
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Component
public class TimingBuilder {
    private InnerBuilder innerBuilder;

    public TimingBuilder() {
    }

    public TimingBuilder start(String message) {
        Objects.requireNonNull(message, "'message' must be not null");

        Instant startInstant = Instant.now();

        if(innerBuilder != null) {
            innerBuilder = innerBuilder.start(startInstant, message);
        } else {
            innerBuilder = new InnerBuilder(Instant.now(), message, null);
        }

        return this;
    }

    public TimingBuilder end() {
        innerBuilder = innerBuilder.end(Instant.now());
        return this;
    }

    public Timing build() {
        if(!innerBuilder.isTopLevel()) {
            throw new IllegalArgumentException("Timing don't finished");
        }

        return innerBuilder.build();
    }

    private static class InnerBuilder {
        private final Instant startInstant;
        private Instant endInstant;
        private final String message;

        private final List<Timing> childList = new ArrayList<Timing>();
        private final InnerBuilder parent;

        private InnerBuilder(Instant startInstant, String message, InnerBuilder parent) {
            this.startInstant = startInstant;
            this.message = message;
            this.parent = parent;
        }

        public boolean isTopLevel() {
            return parent == null;
        }

        public InnerBuilder start(Instant startInstant, String message) {
            if(endInstant != null) {
               throw new IllegalStateException("Method 'end' already called");
            }

            return new InnerBuilder(startInstant, message, this);
        }

        public InnerBuilder end(Instant endInstant) {
            if(this.endInstant != null) {
                throw new IllegalStateException("Method 'end' already called");
            }

            this.endInstant = endInstant;

            if(parent != null) {
                parent.childList.add(build());
                return parent;
            } else {
                return this;
            }
        }

        public Timing build() {
            return Timing.create(startInstant, endInstant, message, childList);
        }
    }
}
