package cloudclub.blog.common.log;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;

@Aspect
@Slf4j
public class BlogLogger {

    @Pointcut("execution(* cloudclub.blog.*.*(..))")
    private void publicTarget(){
    }


    @Before("publicTarget()")
    public void beforeController(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String requestId = request.getRequestId();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        log.info("[{}] #BEFORE >> {}.{}()", requestId, className, method.getName());
        Object[] args = joinPoint.getArgs();
        for(Object arg : args) {
            if(arg != null) {
                log.info("      type = {}", arg.getClass().getSimpleName());
                log.info("      value = {}", arg);
            }
        }
    }

    @After("publicTarget()")
    public void afterController(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String requestId = request.getRequestId();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        log.info("[{}] #After >> {}.{}()", requestId, className, method.getName());
    }

}
