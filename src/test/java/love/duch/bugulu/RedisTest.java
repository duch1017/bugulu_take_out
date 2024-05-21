package love.duch.bugulu;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;

@SpringBootTest
public class RedisTest {
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Test
    public void testString() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("AAA");
        arrayList.add("BBB");
        arrayList.add("CCC");
        System.out.println(Arrays.toString(arrayList.toArray()));
        redisTemplate.opsForValue().set("list", arrayList);
//        redisTemplate.opsForValue().set("city", "beijin");
    }

    @Test
    public void testDelete() {
//        redisTemplate.opsForValue().set("name", "tom");
        redisTemplate.delete("name");
    }

}
