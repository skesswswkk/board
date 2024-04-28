package com.example.boardserver.aop;

import com.example.boardserver.utils.SessionUtil;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;



//kjh aop2
@Component
@Aspect
@Order(Ordered.LOWEST_PRECEDENCE)
@Log4j2
//재사용성: 해당 Aspect는 로그인 체크가 필요한 모든 메서드에 적용될 수 있어 코드 재사용을 촉진합니다.
public class LoginCheckAspect {
    //@Around 어노테이션을 사용하여 @LoginCheck 어노테이션이 붙은 메서드를 가로채서 처리합니다.
    @Around("@annotation(com.example.boardserver.aop.LoginCheck) && @ annotation(loginCheck)")
    public Object adminLoginCheck(ProceedingJoinPoint proceedingJoinPoint, LoginCheck loginCheck) throws Throwable {
        HttpSession session = (HttpSession) ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest().getSession();
        String id = null;
        int idIndex = 0;

        String userType = loginCheck.type().toString();
        switch (userType) {
            case "ADMIN": {
                id = SessionUtil.getLoginAdminId(session);
                break;
            }
            case "USER": {
                id = SessionUtil.getLoginMemberId(session);
                break;
            }
        }
        if (id == null) {
            log.debug(proceedingJoinPoint.toString()+ "accountName :" + id);
            throw new HttpStatusCodeException(HttpStatus.UNAUTHORIZED, "로그인한 id값을 확인해주세요.") {};
        }

        Object[] modifiedArgs = proceedingJoinPoint.getArgs();

        if(proceedingJoinPoint.getArgs()!=null) modifiedArgs[idIndex] = id;

        return proceedingJoinPoint.proceed(modifiedArgs);
    }

}