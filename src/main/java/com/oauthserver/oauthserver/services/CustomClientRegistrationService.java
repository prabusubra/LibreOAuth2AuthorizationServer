package com.oauthserver.oauthserver.services;

import com.oauthserver.oauthserver.beans.CustomClientDetails;
import com.oauthserver.oauthserver.repositories.ClientDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientAlreadyExistsException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientRegistrationService;
import org.springframework.security.oauth2.provider.NoSuchClientException;

import java.util.Arrays;
import java.util.List;

public class CustomClientRegistrationService  implements ClientRegistrationService {

    @Autowired
    private ClientDetailsRepository clientDetailsRepository;

    @Override
    public void addClientDetails(ClientDetails clientDetails) throws ClientAlreadyExistsException {
        //this.clientDetailsRepository.save(clientDetails);
    }

    @Override
    public void updateClientDetails(ClientDetails clientDetails) throws NoSuchClientException {
        //clientDetailsRepository

    }

    @Override
    public void updateClientSecret(String s, String s1) throws NoSuchClientException {

    }

    @Override
    public void removeClientDetails(String s) throws NoSuchClientException {
        this.clientDetailsRepository.deleteById(s);
    }

    @Override
    public List<ClientDetails> listClientDetails() {
        //return this.clientDetailsRepository.findAll();
        return Arrays.asList();
    }
}
