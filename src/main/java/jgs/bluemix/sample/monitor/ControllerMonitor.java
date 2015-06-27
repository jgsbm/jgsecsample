package jgs.bluemix.sample.monitor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Controllerの呼び出しと終了をログ出力するAOPです.
 *
 * @author ryozo
 */
@Aspect
@Component
public class ControllerMonitor {

    private static final Logger logger = LoggerFactory.getLogger(ControllerMonitor.class);

    @Around("execution(* jgs..web.*Controller.*(..))")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = MethodSignature.class.cast(point.getSignature());
        logger.info(
                "Start Controller {}#{}({})",
                signature.getDeclaringTypeName(),
                signature.getMethod().getName(),
                point.getArgs()
        );
        Object result = point.proceed();
        long start = System.currentTimeMillis();
        logger.info(
                "End Controller {}#{}({}): {} in {}msec",
                signature.getDeclaringType(),
                signature.getMethod().getName(),
                point.getArgs(),
                result,
                System.currentTimeMillis() - start
        );

        return result;
    }

}
