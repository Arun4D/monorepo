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

import in.co.ad.springbootredissample.model.Sample;
import in.co.ad.springbootredissample.service.SampleService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/rest/api/sample")
@AllArgsConstructor
public class SampleController {

private SampleService sampleService;

    @GetMapping("/all")
    public ResponseEntity<List<Sample>> getAllEntity() {

        return new ResponseEntity<>(sampleService.getAllEntity(), HttpStatus.OK);

    }


    @GetMapping("/{id}")
    public ResponseEntity<Sample> getEntityById(@PathVariable Long id) {

        return new ResponseEntity<>(sampleService.getEntityById(id), HttpStatus.OK);

    }


    @PostMapping("/")
    public ResponseEntity<Sample> add(@RequestBody Sample entity) {
        return new ResponseEntity<>(sampleService.update(entity), HttpStatus.CREATED); 
    }

    @PutMapping("/")
    public ResponseEntity<Sample> update(@RequestBody Sample sample) {
        return new ResponseEntity<>(sampleService.update(sample), HttpStatus.CREATED); 
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEntityById(@PathVariable Long id) {

        sampleService.delete(id);

        return new ResponseEntity<>("Deleted sample id: " + id, HttpStatus.OK);

    }
}
