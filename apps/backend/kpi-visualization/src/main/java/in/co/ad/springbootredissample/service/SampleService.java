package in.co.ad.springbootredissample.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import in.co.ad.springbootredissample.model.Sample;
import in.co.ad.springbootredissample.repository.SampleRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class SampleService {

    private SampleRepository sampleRepository;

    @Cacheable(value = "sampleList")
    public List<Sample> getAllEntity() {
        List<Sample> list = new ArrayList<>();
        sampleRepository.findAll().forEach(list::add);

        return list;
    }

    @Cacheable(value = "sample", key = "#id")
    public Sample getEntityById(Long id) {
        Sample sample = sampleRepository.findById(id).orElse(null);
        return sample;

    }

    @Caching(evict = { @CacheEvict(value = "sampleList", allEntries = true) }, put = {
            @CachePut(value = "sample", key = "#sample.id") })
    public Sample save(Sample sample) {
        sample = sampleRepository.save(sample);
        return sample;
    }

    @Caching(evict = { @CacheEvict(value = "sampleList", allEntries = true) }, put = {
            @CachePut(value = "sample", key = "#sample.id") })
    public Sample update(Sample sample) {
        sample = sampleRepository.save(sample);
        return sample;
    }

    @Caching(evict = { @CacheEvict(value = "sampleList", allEntries = true),
            @CacheEvict(value = "sample", key = "#id") })
    public void delete(Long id) {
        sampleRepository.deleteById(id);
    }

}
