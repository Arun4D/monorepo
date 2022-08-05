package in.co.ad.springbootredissample.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import in.co.ad.springbootredissample.config.TestRedisConfiguration;
import in.co.ad.springbootredissample.model.RedisSample;
import in.co.ad.springbootredissample.repository.RedisSampleRepository;
import lombok.extern.slf4j.Slf4j;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestRedisConfiguration.class)
@Slf4j
@ActiveProfiles({"test"})
public class TestRedisSampleService {

    @MockBean
    private RedisSampleRepository redisSampleRepository;

    @InjectMocks
    private RedisSampleService redisSampleService;

    @BeforeEach
    public void setup() {

        redisSampleService = new RedisSampleService(redisSampleRepository);
        RedisSample redisSample = new RedisSample();
        redisSample.setId(1L);
        redisSample.setStartDate(new Date());

        RedisSample redisSample2 = new RedisSample();
        redisSample2.setId(2L);
        redisSample2.setStartDate(new Date());

        List<RedisSample> all = new ArrayList<>();
        all.add(redisSample);
        all.add(redisSample2);

        MockitoAnnotations.openMocks(this);
        Mockito.when(redisSampleRepository.save(any(RedisSample.class))).thenReturn(redisSample);
        Mockito.when(redisSampleRepository.findAll()).thenReturn(all);
        Mockito.when(redisSampleRepository.findById(redisSample.getId())).thenReturn(Optional.of(redisSample));

    }

    @Test
    public void shouldSaveSample_toRedis() {

        RedisSample redisSample = new RedisSample();
        redisSample.setId(1L);
        redisSample.setStartDate(new Date());

        RedisSample savedRedisSample = redisSampleService.save(redisSample);
        log.info("Save ; ID: " + savedRedisSample.getId() + ", StartDate: " + savedRedisSample.getStartDate());
        assertNotNull(savedRedisSample.getId());
    }

    @Test
    public void shouldUpateSample_toRedis() {

        RedisSample redisSample = new RedisSample();
        redisSample.setId(1L);
        redisSample.setStartDate(new Date());

        RedisSample savedRedisSample = redisSampleService.save(redisSample);
        log.info("Save ; ID: " + savedRedisSample.getId() + ", StartDate: " + savedRedisSample.getStartDate());
        assertEquals(1L, savedRedisSample.getId());

        redisSample.setStartDate(new Date(Calendar.getInstance().getTimeInMillis() + (10 * 60 * 1000)));

        Mockito.when(redisSampleRepository.save(any(RedisSample.class))).thenReturn(redisSample);

        RedisSample updateRedisSample = redisSampleService.update(redisSample);

        log.info("Update  ; After Date: " + updateRedisSample.getStartDate() + ", Before Date: " + savedRedisSample.getStartDate());

        assertEquals(true, updateRedisSample.getStartDate().after(savedRedisSample.getStartDate()));

    }
}
