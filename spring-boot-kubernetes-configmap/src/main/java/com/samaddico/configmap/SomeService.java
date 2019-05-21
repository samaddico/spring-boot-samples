package com.samaddico.configmap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SomeService {

        @Autowired
        private ApplicationProperties applicationProperties;

        @Scheduled(fixedDelay = 5000)
        public void hello() {
            System.out.println("The balance url is " + this.applicationProperties.getGetbalance());
            System.out.println("The transfer url is " + this.applicationProperties.getTransferfund());
            System.out.println("The customerinfo url is " + this.applicationProperties.getCustomerinfo());
        }


}
