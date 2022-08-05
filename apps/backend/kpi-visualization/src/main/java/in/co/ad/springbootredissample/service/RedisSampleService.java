package in.co.ad.springbootredissample.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import in.co.ad.springbootredissample.model.RedisSample;
import in.co.ad.springbootredissample.repository.RedisSampleRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RedisSampleService {

    private RedisSampleRepository redisSampleRepository;

    public List<RedisSample> getAllEntity() {
        List<RedisSample> list = new ArrayList<>();
        redisSampleRepository.findAll().forEach(list::add);

        return list;
    }

    public RedisSample getEntityById(Long id) {
        RedisSample sample = redisSampleRepository.findById(id).orElse(null);
        return sample;

    }

    public RedisSample save(RedisSample sample) {
        sample = redisSampleRepository.save(sample);
        return sample;
    }

    public RedisSample update(RedisSample sample) {
        sample = redisSampleRepository.save(sample);
        return sample;
    }

    public void delete(Long id) {
        redisSampleRepository.deleteById(id);
    }

}
