package com.samddico.playground;


import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MicrometerService {

    @Autowired
    private MeterRegistry meterRegistry;

    public Counter getCounter() {
        return meterRegistry.counter("api_request_count");
    }
}
