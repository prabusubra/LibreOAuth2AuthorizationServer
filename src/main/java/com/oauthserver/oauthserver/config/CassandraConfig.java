package com.oauthserver.oauthserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;

import java.util.Arrays;
import java.util.List;

@Configuration
public class CassandraConfig extends AbstractCassandraConfiguration {
  @Override
  protected String getKeyspaceName() {
    return "oauthdb";
  }

  @Override
  protected String getContactPoints() {
    return "127.0.0.1";
  }

  @Override
  protected int getPort() {
    return 9042;
  }

  @Override
  protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {

    return Arrays.asList(
        CreateKeyspaceSpecification.createKeyspace(getKeyspaceName())
            .ifNotExists()
            .withSimpleReplication(3));
  }

  @Override
  protected List<String> getStartupScripts() {
    String cqlscript1 =
        "CREATE TABLE IF NOT EXISTS "
            + this.getKeyspaceName()
            + ".clientDetails ("
            + "clientid TEXT PRIMARY KEY ,"
            + "clientsecret TEXT ,"
            + "authorizedgranttypes SET<TEXT> ,"
            + "registeredredirecturi SET<TEXT>, "
            + "scope SET<TEXT>,"
            + "accesstokenvalidityseconds VARINT,"
            + "refreshtokenvalidityseconds VARINT"
            + ");";
    String cqlscript2 =
        "INSERT INTO oauthdb.clientDetails"
            + "(clientid, clientsecret, authorizedgranttypes, "
            + "registeredredirecturi, scope, accesstokenvalidityseconds,"
            + " refreshtokenvalidityseconds)"
            + "VALUES "
            + "('alpha','alpha_secret',"
            + "{'authorization_code', 'implicit', 'client_credentials', 'password'}," +
                "{'https://medium.com'}," +
                "{'Read','Write','Full'},3600,21600)";
    String cqlscript3 =
            "INSERT INTO oauthdb.clientDetails"
                    + "(clientid, clientsecret, authorizedgranttypes, "
                    + "registeredredirecturi, scope, accesstokenvalidityseconds,"
                    + " refreshtokenvalidityseconds)"
                    + "VALUES "
                    + "('beta','beta_secret',"
                    + "{'authorization_code', 'implicit', 'client_credentials', 'password'}," +
                    "{'https://github.com/'}," +
                    "{'Read','Write','Full'},3600,21600)";
    String cqlscript4 =
            "INSERT INTO oauthdb.clientDetails"
                    + "(clientid, clientsecret, authorizedgranttypes, "
                    + "registeredredirecturi, scope, accesstokenvalidityseconds,"
                    + " refreshtokenvalidityseconds)"
                    + "VALUES "
                    + "('gamma','gamma_secret',"
                    + "{'authorization_code', 'implicit', 'client_credentials', 'password'}," +
                    "{'https://about.gitlab.com/'}," +
                    "{'Read','Write','Full'},3600,21600)";
    return Arrays.asList(cqlscript1,cqlscript2,cqlscript3,cqlscript4);
  }
}
