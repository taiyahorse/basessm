package com.base.aspect;

import com.base.conf.DynamicDataSource;
import com.base.conf.SwitchDataSource;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import java.lang.reflect.Method;

@Aspect
@Component
public class SwitchDataSourceAspect {
    @Pointcut("@annotation(com.base.conf.SwitchDataSource)")
    public void dataSourcePointCut() {

    }

    @Around("dataSourcePointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();

        SwitchDataSource dataSource = method.getAnnotation(SwitchDataSource.class);
        if(dataSource == null){
            DynamicDataSource.setDataSource("master");
        }else {
            DynamicDataSource.setDataSource(dataSource.name());
        }

        try {
            return point.proceed();
        } finally {
            DynamicDataSource.clearDataSource();
        }
    }
}
