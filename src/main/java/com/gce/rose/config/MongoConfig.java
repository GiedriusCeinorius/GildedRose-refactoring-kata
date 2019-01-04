package com.gce.rose.config;

import com.mongodb.MongoClient;
import cz.jirutka.spring.embedmongo.EmbeddedMongoFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Configuration
public class MongoConfig {

    private static final String MONGO_DB_URL = "localhost";
    private static final String MONGO_DB_NAME = "embeded_db";
    @Bean
    public MongoTemplate mongoTemplate() throws IOException {

//        String tempFile = System.getenv("temp") + File.separator + "extract-" + System.getenv("USERNAME") + "-extractmongod";
//        String executable;
//        if (System.getenv("OS") != null && System.getenv("OS").contains("Windows")) {
//            executable = tempFile + ".exe";
//        } else {
//            executable = tempFile + ".sh";
//        }
//        Files.deleteIfExists(new File(executable).toPath());
//        Files.deleteIfExists(new File(tempFile + ".pid").toPath());

        EmbeddedMongoFactoryBean mongo = new EmbeddedMongoFactoryBean();
        mongo.setBindIp(MONGO_DB_URL);
        MongoClient mongoClient = mongo.getObject();
        MongoTemplate mongoTemplate = new MongoTemplate(mongoClient, MONGO_DB_NAME);
        return mongoTemplate;
    }
}