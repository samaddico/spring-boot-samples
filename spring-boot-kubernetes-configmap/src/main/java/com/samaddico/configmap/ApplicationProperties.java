package com.samaddico.configmap;


import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Data
@Configuration
@ConfigurationProperties(prefix = "webservice.endpoint")
public class ApplicationProperties {

    private String transferfund;
    private String getbalance;
    private String customerinfo;

}
