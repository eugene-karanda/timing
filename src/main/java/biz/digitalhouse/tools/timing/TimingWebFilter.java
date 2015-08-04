package biz.digitalhouse.tools.timing;

import biz.digitalhouse.tools.timing.format.DefaultTimingFormat;
import biz.digitalhouse.tools.timing.format.TimingFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author eugene.karanda
 * @version 1.0 Create: 02.08.2015 18:29
 */
public class TimingWebFilter extends OncePerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(TimingWebFilter.class);

    private TimingFormat timingFormat = new DefaultTimingFormat();

    @Autowired
    private TimingBuilder timingBuilder;

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        timingBuilder.start("Execution of the request: " + request.getRequestURI());

        try {
            if(filterChain != null) {
                filterChain.doFilter(request, response);
            }
        } finally {
            timingBuilder.end();
        }

        Timing timing = timingBuilder.build();
        logger.info(timingFormat.format(timing));
    }
}
