package in.co.ad.springbootredissample;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import in.co.ad.springbootredissample.config.TestRedisConfiguration;
import lombok.extern.slf4j.Slf4j;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestRedisConfiguration.class)
@Slf4j
@ActiveProfiles({"test"})
class SpringBootRedisSampleApplicationTests {

	@Test
	void contextLoads() {
		log.info("context loaded successfully.");
	}

}
