package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import com.techelevator.util.BasicLogger;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.*;

public class TenmoService {
    private final RestTemplate restTemplate = new RestTemplate();


    private String apiBaseUrl;
    private AuthenticatedUser currentUser;

    public TenmoService(String apiBaseUrl, AuthenticatedUser currentUser){
        this.apiBaseUrl = apiBaseUrl;
        this.currentUser = currentUser;
    }

    public Account getAccountFromUser() {
        String url = apiBaseUrl + "accounts";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBearerAuth(currentUser.getToken());
        HttpEntity<Void> request = new HttpEntity<Void>(httpHeaders);
        Account accountReturnedFromApi = null;
        try {
            ResponseEntity<Account> response = restTemplate.exchange(url, HttpMethod.GET, request, Account.class);
            accountReturnedFromApi = response.getBody();
        } catch (ResourceAccessException e) {
            BasicLogger.log("Unable to connect to server");
        } catch (RestClientResponseException e) {
            BasicLogger.log(e.getRawStatusCode() + " : " + e.getStatusText());
        }
        return accountReturnedFromApi;

    }
    public Transfer transferBetweenAccounts(Transfer transfer){
        String url = apiBaseUrl + "accounts/transfers";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setBearerAuth(currentUser.getToken());
        HttpEntity<Transfer> request = new HttpEntity<Transfer>(transfer,httpHeaders);
        Transfer returnedTransfer = null;
        try {
            ResponseEntity<Transfer> entity = restTemplate.exchange(url,HttpMethod.POST,request,Transfer.class);
            returnedTransfer = entity.getBody();
        } catch (ResourceAccessException e) {
            BasicLogger.log("Unable to connect to server");
        } catch (RestClientResponseException e) {
            BasicLogger.log(e.getRawStatusCode() + " : " + e.getStatusText());
        }
        return returnedTransfer;
    }

    public User[] listUsers(){
        String url = apiBaseUrl + "accounts/list";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBearerAuth(currentUser.getToken());
        HttpEntity<Void> request = new HttpEntity<Void>(httpHeaders);

        User[] users = null;
        try {
        ResponseEntity<User[]> entity = restTemplate.exchange(url,HttpMethod.GET,request,User[].class);
        users = entity.getBody();
        } catch (ResourceAccessException e) {
            BasicLogger.log("Unable to connect to server");
        } catch (RestClientResponseException e) {
            BasicLogger.log(e.getRawStatusCode() + " : " + e.getStatusText());
        }
        return users;
    }
}
