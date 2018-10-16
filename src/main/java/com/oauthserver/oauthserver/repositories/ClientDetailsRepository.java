package com.oauthserver.oauthserver.repositories;

import com.oauthserver.oauthserver.beans.CustomClientDetails;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientDetailsRepository extends CassandraRepository<CustomClientDetails,String> {
    @Query("SELECT * FROM oauthdb.clientDetails where clientid = :clientid")
    public CustomClientDetails findByClientId(@Param("clientid") String clientid);

    //public CustomClientDetails save(ClientDetails customClientDetails);
}
