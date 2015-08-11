package biz.digitalhouse.tools.timing;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ClassUtils;

import java.text.MessageFormat;

/**
 * @author eugene.karanda
 * @version 1.0 Create: 03.08.2015 20:26
 */
@Aspect
public class TimingAspect {

    @Autowired
    private TimingBuilder timingBuilder;

    @Around("@annotation(Measured)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        TimingBuilder.Token token = timingBuilder.start(buildMessage(joinPoint));
        try {
            return joinPoint.proceed();
        } finally {
            timingBuilder.end(token);
        }
    }

    private String buildMessage(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        return MessageFormat.format("{0}.{1}({2})", signature.getDeclaringType().getSimpleName(),
                signature.getName(),
                ClassUtils.classNamesToString(signature.getParameterTypes()));
    }
}
