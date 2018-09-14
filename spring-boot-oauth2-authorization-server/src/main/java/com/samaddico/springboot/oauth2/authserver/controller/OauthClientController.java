package com.samaddico.springboot.oauth2.authserver.controller;


import com.samaddico.springboot.oauth2.authserver.exception.MissingParamterException;
import com.samaddico.springboot.oauth2.authserver.model.JsonResponse;
import com.samaddico.springboot.oauth2.authserver.model.OAuthClientDetails;
import com.samaddico.springboot.oauth2.authserver.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@ControllerAdvice
@RequestMapping("/oauth/client")
@RestController
public class OauthClientController {


    @Autowired
    private JdbcClientDetailsService clientDetailsService;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public JsonResponse save(@RequestBody @Valid OAuthClientDetails oAuthClientDetails){



        ClientDetails clientDetails = oAuthClientDetails ;
        clientDetailsService.addClientDetails(clientDetails);

        JsonResponse response = new JsonResponse();
        response.setStatus(true);
        response.setMessage(ResponseMessage.SUCCESS.toString());
        response.setData(clientDetailsService.loadClientByClientId(clientDetails.getClientId()));//return the client details
        return response;
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public JsonResponse updateClient(@RequestBody @Valid OAuthClientDetails oAuthClientDetails){

        ClientDetails clientDetails = oAuthClientDetails ;
        clientDetailsService.updateClientDetails(clientDetails);

        JsonResponse response = new JsonResponse();
        response.setStatus(true);
        response.setMessage(ResponseMessage.SUCCESS.toString());
        response.setData(clientDetailsService.loadClientByClientId(clientDetails.getClientId()));//return the client details
        return response;
    }

    @RequestMapping(value = "/list",method = RequestMethod.POST)
    public JsonResponse listClients(){
        JsonResponse response = new JsonResponse();
        response.setStatus(true);
        response.setMessage(ResponseMessage.SUCCESS.toString());
        response.setData(clientDetailsService.listClientDetails());//return the client details
        return response;
    }

    @RequestMapping(value = "/update_secret",method = RequestMethod.POST)
    public JsonResponse updateSecret(@RequestBody Map<String,Object> data) throws MissingParamterException {
        String clientId = (String) data.get("clientId");
        String clientSecret = (String) data.get("clientSecret");

        if(clientId == null || clientSecret == null){
            throw new MissingParamterException("missing parameter clientId or clientSecret"); //throw exception here
        }
        clientDetailsService.updateClientSecret(clientId,clientSecret);

        JsonResponse response = new JsonResponse();
        response.setStatus(true);
        response.setMessage(ResponseMessage.SUCCESS.toString());
        response.setData(clientDetailsService.loadClientByClientId(clientId));//return the client details
        return response;
    }

    @ExceptionHandler(value = MissingParamterException.class)
    public JsonResponse missingParam(MissingParamterException mpe){
        JsonResponse response = new JsonResponse();
        response.setStatus(false);
        response.setMessage(mpe.getMessage());
        return response;
    }

}
