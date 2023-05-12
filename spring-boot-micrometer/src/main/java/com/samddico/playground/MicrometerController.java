package com.samddico.playground;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MicrometerController {

    private final MicrometerService micrometerService;

    @Autowired
    public MicrometerController(MicrometerService micrometerService) {
        this.micrometerService = micrometerService;
    }

    @GetMapping("/api")
    public ResponseEntity someApi() {
        micrometerService.getCounter().increment();
        return  ResponseEntity.status(HttpStatus.OK).build();
    }
}
