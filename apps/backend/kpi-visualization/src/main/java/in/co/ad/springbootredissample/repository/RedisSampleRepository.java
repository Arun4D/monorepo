package in.co.ad.springbootredissample.repository;

import org.springframework.data.repository.CrudRepository;

import in.co.ad.springbootredissample.model.RedisSample;

public interface RedisSampleRepository extends CrudRepository<RedisSample, Long> {
    
}
