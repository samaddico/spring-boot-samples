package com.samaddico.springboot.oauth2.authserver.controller;


import com.samaddico.springboot.oauth2.authserver.AuthServerMain;
import com.samaddico.springboot.oauth2.authserver.model.OAuthClientDetails;
import com.samaddico.springboot.oauth2.authserver.utils.ResponseMessage;
import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;
import com.jayway.restassured.response.Header;
import org.apache.commons.codec.binary.Base64;
import org.hamcrest.Matchers;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static com.jayway.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AuthServerMain.class)
@WebAppConfiguration
@ActiveProfiles("mvc")
public class OAuthClientControllerTest {

    @Autowired
    private OauthClientController oauthClientController;

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Autowired
    private JdbcClientDetailsService jdbcClientDetailsService;

    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    private String accessToken;

    @Before
    public void setup() throws Exception{

        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).addFilter(springSecurityFilterChain).build();
        RestAssuredMockMvc.mockMvc = this.mockMvc;


        OAuthClientDetails clientDetails = new OAuthClientDetails();
        clientDetails.setScoped(true);
        clientDetails.setClientId("test-client");
        clientDetails.setClientSecret("client1234");
        clientDetails.setResourceIds(new HashSet<>(Arrays.asList("auth-server","resource-2")));
        clientDetails.setAuthorizedGrantTypes(new HashSet<>(Arrays.asList("client_credentials", "authorization_code", "implicit", "password","refresh_token")));
        clientDetails.setScope(new HashSet(Arrays.asList("trust", "read", "write")));
        clientDetails.setActive(true);
        clientDetails.setAutoApprove(true);
        clientDetails.setSecretRequired(true);
        clientDetails.setAccessTokenValiditySeconds(3600);
        clientDetails.setRefreshTokenValiditySeconds(3600);
        clientDetails.setAuthorities(new HashSet(Arrays.asList("trust", "read", "write")));

        Map<String,Object> additionalInfo = new HashMap<>();
        additionalInfo.put("client_name","test-client");

        clientDetails.setAdditionalInformation(additionalInfo);
        jdbcClientDetailsService.addClientDetails(clientDetails);

        accessToken  = getAccessToken();
    }

    @Test
    public void whenNoCredentialsPassed_ThenReturnUnAuthorised() {
        given()
                .log()
                .all().contentType("application/json")
                .post("/oauth/token").
                then().
                statusCode(HttpStatus.UNAUTHORIZED.value());
    }

    @Test
    public void whenClientDetailIsParsed_thenSaveData() {

        Map<String,Object> payload = new HashMap<>();
        payload.put("clientId","test-app");
        payload.put("clientSecret","test@123");
        payload.put("refreshTokenValiditySeconds",4500);
        payload.put("accessTokenValiditySeconds",4500);
        payload.put("active",true);
        payload.put("scoped",true);
        payload.put("scope",Arrays.asList("trust", "read", "write"));
        payload.put("secretRequired",false);
        payload.put("autoApprove",false);
        payload.put("resourceIds",new HashSet<>(Arrays.asList("auth-server","employee-app")));
        payload.put("authorities",new HashSet(Arrays.asList("trust", "read", "write")));
        payload.put("authorizedGrantTypes",new HashSet<>(Arrays.asList("client_credentials", "authorization_code", "implicit", "password","refresh_token")));
        Map<String,Object> additionalInfo = new HashMap<>();
        additionalInfo.put("client_name","test-app");
        payload.put("additionalInformation",additionalInfo);

        given()
                .log()
                .all().contentType("application/json")
                .header(new Header("Authorization","Bearer " + accessToken))
                .body(payload)
                .when()
                .post("/oauth/client/add")
                .then()
                .statusCode(200)
                .body("status", Matchers.is(true))
                .body("message", Matchers.is(ResponseMessage.SUCCESS.toString()));
    }

    private String getAccessToken() throws Exception {
        final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

        ResultActions result = mockMvc.perform(post("/oauth/token?grant_type=client_credentials")
                .params(params)
                .with(httpBasic("test-client", "client1234"))
                .accept("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"));


        String resultString = result.andReturn().getResponse().getContentAsString();

        JacksonJsonParser jsonParser = new JacksonJsonParser();
        return jsonParser.parseMap(resultString).get("access_token").toString();
    }

    @Test
    public void whenClientCredentialsIsPassed_theGetAccessToken(){

        String authCookie = ("test-client" + ":" + "client1234");
        String authEncoded =
                new String(Base64.encodeBase64(authCookie.getBytes()));
        Map<String,Object> payload = new HashMap<>();

        given()
                .log()
                .all().contentType("application/json")
                .header(new Header("Authorization","Basic " + authEncoded.toString()))
                .body(payload)
                .when()
                .post("/oauth/token?grant_type=client_credentials")
                .then()
                .statusCode(200)
               .body("$", Matchers.hasKey("access_token"))
               .body("$", Matchers.hasKey("token_type"))
               .body("$", Matchers.hasKey("expires_in"))
               .body("$", Matchers.hasKey("scope"))
               .body("$", Matchers.hasKey("app_name"))
               .body("$", Matchers.hasKey("jti"));
    }

    @Test
    public void whenClientSecretIsPassed_thenUpdateClientSecret(){

        Map<String,Object> payload = new HashMap<>();
        payload.put("clientId","test-client");
        payload.put("clientSecret","client123456");//change the secret

        given()
                .log()
                .all().contentType("application/json")
                .header(new Header("Authorization","Bearer " + accessToken))
                .body(payload)
                .when()
                .post("/oauth/client/update_secret")
                .then()
                .statusCode(200)
                .body("status", Matchers.is(true))
                .body("message", Matchers.is(ResponseMessage.SUCCESS.toString()))
                .body("data.client_id", Matchers.is(payload.get("clientId")));
    }

    @Test
    public void whenClientSecretIsNotPassed_thenThrowException()  {

        Map<String,Object> payload = new HashMap<>();

        given()
                .log()
                .all().contentType("application/json")
                .header(new Header("Authorization","Bearer " + accessToken))
                .body(payload)
                .when()
                .post("/oauth/client/update_secret")
                .then()
                .statusCode(200)
                .body("status", Matchers.is(false));
    }

    @Test
    public void whenEndpointIsCalled_thenReturnAllClientDetails(){

       given()
                .log()
                .all().contentType("application/json")
               .header(new Header("Authorization","Bearer " + accessToken))
                .when()
                .post("/oauth/client/list")
                .then()
                .statusCode(200)
                .body("status", Matchers.is(true))
                .body("message", Matchers.is(ResponseMessage.SUCCESS.toString()))
                .body("data", Matchers.is(Matchers.hasSize(Matchers.greaterThan(0))));
    }


    @Test
    public void whenClientDetailsIsPassed_thenUpdateClientDetails(){

        Map<String,Object> payload = new HashMap<>();
        payload.put("clientId","test-client");
        payload.put("clientSecret","secret10");
        payload.put("refreshTokenValiditySeconds",4500);
        payload.put("accessTokenValiditySeconds",4500);
        payload.put("active",true);
        payload.put("scoped",true);
        payload.put("scope",Arrays.asList("trust", "read", "write"));
        payload.put("secretRequired",false);
        payload.put("autoApprove",false);
        payload.put("resourceIds",new HashSet<>(Arrays.asList("auth-server","employee-app")));
        payload.put("authorities",new HashSet(Arrays.asList("trust", "read", "write")));
        payload.put("authorizedGrantTypes",new HashSet<>(Arrays.asList("client_credentials", "authorization_code", "implicit", "password","refresh_token")));
        Map<String,Object> additionalInfo = new HashMap<>();
        additionalInfo.put("client_name","test-app");
        payload.put("additionalInformation",additionalInfo);

       given()
                .log()
                .all().contentType("application/json")
                .header(new Header("Authorization","Bearer " + accessToken))
                .body(payload)
                .when()
                .post("/oauth/client/update")
                .then()
                .statusCode(200)
                .body("status", Matchers.is(true))
                .body("message", Matchers.is(ResponseMessage.SUCCESS.toString()))
                .body("data.client_id", Matchers.is(payload.get("clientId")));
    }

    @After
    public void tearDown(){
        jdbcClientDetailsService.removeClientDetails("test-client");
    }

}
