package biz.digitalhouse.tools.timing;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * @author eugene.karanda
 * @version 1.0 Create: 03.08.2015 20:26
 */
@Aspect
public class TimingAspect {
    @Around("execution(* *(..)) " +
            "&& @annotation(Measured)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Hello aspect's");
        return joinPoint.proceed();
    }
}
