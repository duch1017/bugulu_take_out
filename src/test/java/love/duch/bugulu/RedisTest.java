package love.duch.bugulu;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

@SpringBootTest
public class RedisTest {
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Test
    public void testString() {
        redisTemplate.opsForValue().set("city", "beijin");
    }

}
