package com.softplayer.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.softplayer.domain"})
@EnableJpaRepositories(basePackages = {"com.softplayer.repositories"})
@EnableTransactionManagement
public class RepositoryConfig {

}
