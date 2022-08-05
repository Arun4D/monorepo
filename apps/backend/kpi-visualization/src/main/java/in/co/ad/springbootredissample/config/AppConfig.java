package in.co.ad.springbootredissample.config;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Profile("prod")
@Configuration
@EnableCaching
@EnableRedisRepositories
@EnableTransactionManagement
public class AppConfig {

    @Value("${spring.redis.host}")
    private String REDIS_HOST;
    @Value("${spring.redis.port}")
    private int REDIS_PORT;
    @Bean
    public RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer() {
        return (builder) -> builder.withCacheConfiguration("sample", RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(10)));
        
    }

    @Bean
    public RedisCacheConfiguration cacheConfiguration() {
        return RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(60));
    }

  @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration(REDIS_HOST, REDIS_PORT);
        return new JedisConnectionFactory(configuration);
    }

    @Bean
    public RedisTemplate<Long, Object> redisTemplate() {
        
       
        RedisTemplate<Long, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setEnableTransactionSupport(true);
        redisTemplate.setConnectionFactory(jedisConnectionFactory());

        return redisTemplate;
    }
}
