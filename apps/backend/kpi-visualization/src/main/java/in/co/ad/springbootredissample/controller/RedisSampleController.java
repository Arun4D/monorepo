package in.co.ad.springbootredissample.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.co.ad.springbootredissample.model.RedisSample;
import in.co.ad.springbootredissample.service.RedisSampleService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/rest/api/redis/sample")
@AllArgsConstructor
public class RedisSampleController {

    private RedisSampleService redisSampleService;

    @GetMapping("/all")
    public ResponseEntity<List<RedisSample>> getAllEntity() {

        return new ResponseEntity<>(redisSampleService.getAllEntity(), HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<RedisSample> getEntityById(@PathVariable Long id) {

        return new ResponseEntity<>(redisSampleService.getEntityById(id), HttpStatus.OK);

    }

    @PostMapping("/")
    public ResponseEntity<RedisSample> add(@RequestBody RedisSample entity) {
        return new ResponseEntity<>(redisSampleService.update(entity), HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<RedisSample> update(@RequestBody RedisSample sample) {
        return new ResponseEntity<>(redisSampleService.update(sample), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEntityById(@PathVariable Long id) {

        redisSampleService.delete(id);

        return new ResponseEntity<>("Deleted sample id: " + id, HttpStatus.OK);

    }
}
