package com.teleone.mytele.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource getDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
        config.setDriverClassName("org.postgresql.Driver");
        config.setUsername("postgres");
        config.setPassword("12345");
        config.addDataSourceProperty("serverName", "localhost");
        config.addDataSourceProperty("port", "5432");
        config.addDataSourceProperty("databaseName", "postgres");
        config.setPoolName("springHikariCP");

        return new HikariDataSource(config);
    }
}