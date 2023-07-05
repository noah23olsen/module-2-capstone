package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.AuthenticatedUser;
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

        ResponseEntity<Account> response = restTemplate.exchange(url, HttpMethod.GET, request, Account.class);
        Account accountReturnedFromApi = response.getBody();
        return accountReturnedFromApi;

    }
}
