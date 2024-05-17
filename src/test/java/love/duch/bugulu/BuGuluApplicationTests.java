package love.duch.bugulu;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import redis.clients.jedis.Jedis;

import java.util.*;

//@SpringBootTest
class BuGuluApplicationTests {

    @Test
    public void testUuid() {
        for (int i = 0; i < 1000; i++) {
            System.out.println(UUID.randomUUID().toString());
        }
    }

    @Value("${test.aaa}")
    private String aaa;

    @Test
    void testAAA() {
        System.out.println(aaa);
    }

    /**
     * 测试JWT
     */
    @Test
    public void testGenJWT() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 1);
        claims.put("name", "Tom");

        String pwd = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, pwd)//签名算法及密钥
                .setClaims(claims)//自定义内容（载荷）
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 3600))//设置有效期
                .compact();
        System.out.println(jwt);
    }

    @Test
    public void testRedis() {
        Jedis jedis = new Jedis("192.168.10.100", 6379);

        jedis.auth("aaaa");
        jedis.set("name", "zhangsan");
        String name = jedis.get("name");
        System.out.println(name);

        Hashtable<String, String> hashtable = new Hashtable<>();
        hashtable.put("name", "zhangsan");
        hashtable.put("age", "18");
        jedis.hset("hash", "age", "18");
        String hash = jedis.hget("hash", "age");
        System.out.println(hash);

//        jedis.del("name");
        jedis.close();
    }

}
