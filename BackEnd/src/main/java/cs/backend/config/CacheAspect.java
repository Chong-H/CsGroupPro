package cs.backend.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Aspect
@Component
public class CacheAspect {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    // 拦截所有Service层的方法
    @Around("execution(public * cs.backend.service..*.*(..))")
    public Object cache(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("AOP拦截到方法: " + methodName); // 添加这行

        String key = generateKey(joinPoint);
        System.out.println("缓存key: " + key); // 添加这行
        try {
            Object value = redisTemplate.opsForValue().get(key);
            if (value != null) {
                return value;
            }
            value = joinPoint.proceed();
            redisTemplate.opsForValue().set(key, value, 10, TimeUnit.MINUTES); // 10分钟过期
            return value;
        } catch (Exception e) {
            // Redis异常时，直接走数据库
            return joinPoint.proceed();
        }
    }

    private String generateKey(ProceedingJoinPoint joinPoint) {
        StringBuilder key = new StringBuilder(joinPoint.getSignature().toShortString());
        for (Object arg : joinPoint.getArgs()) {
            key.append(":").append(arg == null ? "null" : arg.toString());
        }
        return key.toString();
    }
}