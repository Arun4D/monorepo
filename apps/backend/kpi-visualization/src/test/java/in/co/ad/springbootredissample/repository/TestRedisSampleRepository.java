package in.co.ad.springbootredissample.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import in.co.ad.springbootredissample.config.TestRedisConfiguration;
import in.co.ad.springbootredissample.model.RedisSample;
import lombok.extern.slf4j.Slf4j;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestRedisConfiguration.class)
@Slf4j
@ActiveProfiles({"test"})
public class TestRedisSampleRepository {

    @Mock
    private RedisSampleRepository redisSampleRepository;

    @BeforeEach
    public void setup() {

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
        when(redisSampleRepository.save(any(RedisSample.class))).thenReturn(redisSample);
        Mockito.when(redisSampleRepository.findAll()).thenReturn(all);
        Mockito.when(redisSampleRepository.findById(redisSample.getId())).thenReturn(Optional.of(redisSample));

    }

    @Test
    public void saveSample_success() {

        RedisSample redisSample = new RedisSample();
        redisSample.setId(1L);
        redisSample.setStartDate(new Date());

        RedisSample savedRedisSample =  redisSampleRepository.save(redisSample);
        log.info("Save ; ID: " + savedRedisSample.getId() + ", StartDate: " + savedRedisSample.getStartDate());
        assertEquals(1L, savedRedisSample.getId());
    }
    @Test
    public void findAllSample_success() {


        Iterable<RedisSample> iterable =  redisSampleRepository.findAll();
        int size = 0;
        if (iterable instanceof Collection<?>) {
            size = ((Collection<?>)iterable).size();
        } 
        assertEquals(2, size); 
    }

    @Test
    public void findByIdSample_success() {

        Optional<RedisSample> sampleValue =  redisSampleRepository.findById(1L);
        RedisSample redisSample = sampleValue.orElse(null);
        assertNotNull(redisSample);
        assertEquals(1L, redisSample.getId()); 
    }

}
