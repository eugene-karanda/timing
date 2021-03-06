package biz.digitalhouse.tools.timing;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
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

    public TimingBuilder.Token start(String message) {
        Objects.requireNonNull(message, "'message' must be not null");

        Date now = new Date();

        if(innerBuilder != null) {
            innerBuilder = innerBuilder.start(now, message);
        } else {
            innerBuilder = new InnerBuilder(now, message, null);
        }

        return innerBuilder.getToken();
    }

    public void end(Token token) {
        innerBuilder = innerBuilder.end(new Date(), token);
    }

    public Timing build() {
        if(!innerBuilder.isTopLevel()) {
            throw new IllegalArgumentException("Timing don't finished");
        }

        return innerBuilder.build();
    }

    private static class InnerBuilder {
        private final Date startInstant;
        private Date endInstant;
        private final String message;
        private final Token token = new Token();

        private final List<Timing> childList = new ArrayList<>();
        private final InnerBuilder parent;

        private InnerBuilder(Date startInstant, String message, InnerBuilder parent) {
            this.startInstant = startInstant;
            this.message = message;
            this.parent = parent;
        }

        public boolean isTopLevel() {
            return parent == null;
        }

        public Token getToken() {
            return token;
        }

        public InnerBuilder start(Date startInstant, String message) {
            if(endInstant != null) {
               throw new IllegalStateException("Method 'end' already called");
            }

            return new InnerBuilder(startInstant, message, this);
        }

        public InnerBuilder end(Date endInstant, Token token) {
            if(this.token != token) {
                throw new IllegalArgumentException("Scope token is invalid");
            }

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

    public static class Token {

    }
}
