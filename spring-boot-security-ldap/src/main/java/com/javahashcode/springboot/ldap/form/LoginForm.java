/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dreamoval.orsuite.stanbic.paperless.form;

import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author do-enr-lap-5
 */
public class LoginForm {
    
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    private String clientId;
    

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}
