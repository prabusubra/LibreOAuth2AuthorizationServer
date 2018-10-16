package com.oauthserver.oauthserver.config;

import com.oauthserver.oauthserver.beans.CustomClientDetails;
import com.oauthserver.oauthserver.repositories.ClientDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetails;

import java.util.*;

@Configuration
@EnableAuthorizationServer
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {
  @Autowired private final AuthenticationManager authenticationManager;

  @Autowired private ClientDetailsRepository clientDetailsRepository;

  @Autowired
  public OAuth2Config(AuthenticationConfiguration authenticationConfiguration) throws Exception {
    this.authenticationManager = authenticationConfiguration.getAuthenticationManager();
  }

  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

      clients.withClientDetails( identifier -> {
          System.out.println(" client id from ui "+identifier);
          return clientDetailsRepository.findByClientId(identifier);
      });

  }

  @Override
  public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
    oauthServer
        .tokenKeyAccess("permitAll()")
        .checkTokenAccess("isAuthenticated()")
        .passwordEncoder(NoOpPasswordEncoder.getInstance());
  }

  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    endpoints.authenticationManager(authenticationManager);
  }
}
