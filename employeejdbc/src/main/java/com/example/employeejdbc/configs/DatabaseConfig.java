package com.example.employeejdbc.configs;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class DatabaseConfig {

    @Value("${POSTGRES_DRIVER_CLASS_NAME}")
    private String driverClassName;

    @Value("${POSTGRES_HOST}")
    private String host;

    @Value("${POSTGRES_PORT}")
    private String port;

    @Value("${POSTGRES_DATABASE}")
    private String database;

    @Value("${POSTGRES_USERNAME}")
    private String username;

    @Value("${POSTGRES_PASSWORD}")
    private String password;


    @Bean
    public DataSource getConfigDataSource() throws SQLException {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setJdbcUrl("jdbc:postgresql://" + host + ":" + port + "/" + database);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public JdbcTemplate getJdbcTemplate() throws SQLException {
        return new JdbcTemplate(getConfigDataSource());
    }
}
