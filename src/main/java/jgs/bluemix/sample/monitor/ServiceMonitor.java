package jgs.bluemix.sample.monitor;

import jgs.bluemix.sample.exception.BusinessException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Serviceの呼び出しと終了をログ出力するAOPです.
 *
 * @author ryozo
 */
@Aspect
@Component
public class ServiceMonitor {

    private static final Logger logger = LoggerFactory.getLogger(ServiceMonitor.class);

    @Around("execution(* jgs..service.*Service.*(..))")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = MethodSignature.class.cast(point.getSignature());
        logger.info(
                "Start service {}#{}({})",
                signature.getDeclaringTypeName(),
                signature.getMethod().getName(),
                point.getArgs()
        );
        Object result = point.proceed();
        long start = System.currentTimeMillis();
        logger.info(
                "End service {}#{}({}): {} in {}msec",
                signature.getDeclaringType(),
                signature.getMethod().getName(),
                point.getArgs(),
                result,
                System.currentTimeMillis() - start
        );

        return result;
    }

    /**
     * Service層で発生した{@link BusinessException}に関する情報を出力します.
     * BusinessExceptionはController層でcatchされ、例外処理が行われるため、
     * Controller層よりも外側ではログ出力できません.
     * そのため、Service層の呼び出し後のタイミングでログ出力を行います.
     */
    @AfterThrowing(value = "execution(* jgs..service.*Service.*(..))", throwing = "ex")
    public void throwingBusinessException(JoinPoint point, BusinessException ex) {
        MethodSignature signature = MethodSignature.class.cast(point.getSignature());
        logger.warn(
                "throwing BusinessException in {}#{}({})",
                signature.getDeclaringType(),
                signature.getMethod().getName(),
                point.getArgs(),
                ex);
    }

}
