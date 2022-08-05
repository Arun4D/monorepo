package in.co.ad.springbootredissample.repository;

import org.springframework.data.repository.CrudRepository;

import in.co.ad.springbootredissample.model.Sample;

public interface SampleRepository extends CrudRepository<Sample, Long> {
    
}
