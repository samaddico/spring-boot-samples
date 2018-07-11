package com.samaddico.error.handling.controller;

import com.samaddico.error.handling.exception.MissingParameterException;
import com.samaddico.error.handling.form.JsonResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
@RestController
public class TestController {


    @RequestMapping(value = "/hello")
    private JsonResponse getName(@RequestParam("name") String name) throws MissingParameterException {
        if(name == null || name.isEmpty()){
            throw new MissingParameterException("Parameter 'name' required");
        }

        JsonResponse response = new JsonResponse();
        response.setStatus(true);
        response.setMessage("operation successful");
        response.setData("Hello there, "+ name);

        return response;
    }


    @ExceptionHandler(MissingParameterException.class)
    public JsonResponse assertionException(final MissingParameterException e) {
        JsonResponse response = new JsonResponse();
        response.setStatus(false);
        response.setMessage(e.getMessage());
        response.setData(null);
        return response;
    }
}
