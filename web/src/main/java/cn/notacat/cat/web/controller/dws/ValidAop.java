package cn.notacat.cat.web.controller.dws;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import java.util.Arrays;

/**
 * @author ccod
 * @date 2019/4/24 9:39
 **/

//@Aspect
//@Component
public class ValidAop {
    /*所有方法都会执行此切面*/
    @Pointcut("@annotation(cn.notacat.cat.web.controller.demo.ValidAnn)")
    public void validAop() {
    }

    /*对切面进行字段验证*/
    @Around("validAop()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        if (args != null) {
            Arrays.stream(args)
                    .forEach(arg -> {
                        if (arg instanceof BindingResult) {
                            BindingResult result = (BindingResult) arg;
                            if (result.hasErrors()) {
                                result.getAllErrors()
                                        .stream()
                                        .forEach(err -> {
                                            throw new RuntimeException(err.getDefaultMessage());
                                        });
                            }
                        }
                    });
        }
        return joinPoint.proceed();
    }
}
