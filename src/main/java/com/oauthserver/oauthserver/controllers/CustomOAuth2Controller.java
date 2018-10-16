package com.oauthserver.oauthserver.controllers;

import com.oauthserver.oauthserver.beans.CustomClientDetails;
import com.oauthserver.oauthserver.repositories.ClientDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("clients")
public class CustomOAuth2Controller {

    @Autowired private ClientDetailsRepository clientDetailsRepository;

    @GetMapping
    public List<CustomClientDetails> getAll(){
        return this.clientDetailsRepository.findAll();
    }

    @PostMapping
    public String addClient(@RequestBody CustomClientDetails customClientDetails){
        this.clientDetailsRepository.save(customClientDetails);
        return "Added Successfully";
    }
    @DeleteMapping
    public String removeClient(@RequestParam("clientid") String clientid){
        this.clientDetailsRepository.deleteById(clientid);
        return "Deleted";
    }
}
