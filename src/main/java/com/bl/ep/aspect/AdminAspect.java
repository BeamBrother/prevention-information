package com.bl.ep.aspect;

import com.bl.ep.bean.HealthCode;
import com.bl.ep.bean.Student;
import com.bl.ep.service.HealthCodeService;
import com.bl.ep.service.StudentService;
import com.bl.ep.utils.IdUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @ClassName AdminInsertAspect
 * @Description aop
 * @Author 陈宝梁
 * @Date 2021/12/1 14:54
 * @Version 1.0
 **/
@Aspect
@Component
public class AdminAspect {
    @Resource
    private HealthCodeService healthCodeService;
    @Resource
    private StudentService studentService;

    @AfterReturning("@annotation(com.bl.ep.anno.InsertManagerPower)")
    public void health(JoinPoint joinPoint) throws Exception{
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        String[] parameterNames = signature.getParameterNames();
        Object[] args = joinPoint.getArgs();

        int paramIndex  = ArrayUtils.indexOf(parameterNames, "sno");
        if (paramIndex != -1){
            String sno = (String) args[paramIndex];
            Student stu = studentService.findByAccount(sno);
            if (stu != null){
                HealthCode healthCode = new HealthCode();
                healthCode.setId(IdUtils.getIncreaseIdByCurrentTimeMillis());
                healthCode.sethCode(1);
                healthCode.setStudent(stu);
                int i = healthCodeService.addHealthCode(healthCode);
            }
        }
    }
}
