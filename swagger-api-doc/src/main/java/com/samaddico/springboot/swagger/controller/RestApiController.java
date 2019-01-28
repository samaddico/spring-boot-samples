package com.samaddico.springboot.swagger.controller;

import com.samaddico.springboot.swagger.SwaggerDocumentationApplication;
import com.samaddico.springboot.swagger.form.ApiRequest;
import com.samaddico.springboot.swagger.form.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/api")
@RestController
public class RestApiController {

    private static final String message = "SUCCESS";

    @PostMapping(value = "/contacts", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ApiResponse getContactList(@RequestBody ApiRequest apiRequest){

        ApiResponse response = new ApiResponse();
        response.setMessage(message);
        response.setSuccess(true);
        response.setData(SwaggerDocumentationApplication.getContacts());

        return response;
    }

}
