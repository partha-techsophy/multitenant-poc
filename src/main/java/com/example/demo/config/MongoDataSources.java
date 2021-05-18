package com.example.demo.config;

import com.example.demo.filter.TenantContext;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Configuration
@Slf4j
public class MongoDataSources {

    private static final String USER_NAME = "root";
    private static final String PASSWORD = "example";
    private static final String DEFAULT_DB = "demo";
    private static final String HOST = "localhost";
    private static final String PORT = "27017";

    //This is an in memory store for demo. This can be replaced using Redis
    private Map<String, MongoClient> client = new HashMap<>();

    @PostConstruct
    @Lazy
    public void initTenant() {
        //for testing populate tenant specific details here. These should be stored in Redis and read from Redis
        client.put("T1", createMongoClient());
        client.put("T2", createMongoClient());
    }

    /**
     * This is required for default initialisation
     * @return
     */
    @Bean
    public String databaseName() {
        return DEFAULT_DB;
    }

    /**
     * This is required for default initialisation.
     *
     * @return
     */
    @Bean
    public MongoClient getMongoClient() {
        MongoCredential credential = MongoCredential
                .createScramSha256Credential(System.getenv("USER_NAME"), "admin",
                        System.getenv("PASSWORD").toCharArray());
        MongoClient mongoClient = MongoClients.create(MongoClientSettings.builder()
                .applyToClusterSettings(builder ->
                        builder.hosts(Collections
                                .singletonList(new ServerAddress(System.getenv("MONGO_HOST"),
                                        Integer.parseInt(System.getenv("MONGO_PORT"))))))
                .credential(credential)
                .build());
        client.put(DEFAULT_DB, mongoClient);
        return mongoClient;
    }


    /**
     * For each tenant host/user may be different.
     * For this POC it is using the same host.
     * @return
     */
    //this is for testing
    private MongoClient createMongoClient() {
        MongoCredential credential = MongoCredential
                .createScramSha256Credential(System.getenv("USER_NAME"), "admin",
                        System.getenv("PASSWORD").toCharArray());
        return MongoClients.create(MongoClientSettings.builder()
                .applyToClusterSettings(builder ->
                        builder.hosts(Collections
                                .singletonList(new ServerAddress(System.getenv("MONGO_HOST"),
                                        Integer.parseInt(System.getenv("MONGO_PORT"))))))
                .credential(credential)
                .build());
    }

    public MongoDatabase getDatabase() {
        //Read from redis
        return client.get(TenantContext.getTenantId()).getDatabase(TenantContext.getTenantId());
    }
}
