package com.vaadin.artur.datausecases.util;

import com.zaxxer.hikari.*;
import java.net.URI;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

@Configuration
@Conditional(HasDatabaseUrl.class)
public class HerokuDBConfig {
    @Value("${DATABASE_URL:#{null}}")
    private URI herokuDbUri;

    @Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();

        if (herokuDbUri != null) {
            String username = herokuDbUri.getUserInfo().split(":")[0];
            String password = herokuDbUri.getUserInfo().split(":")[1];
            String host = herokuDbUri.getHost();
            int port = herokuDbUri.getPort();
            String db = herokuDbUri.getPath();
            String jdbcUrl = "jdbc:postgresql://" + host + ':' + port + db;

            config.setJdbcUrl(jdbcUrl);
            config.setUsername(username);
            config.setPassword(password);
        }

        return new HikariDataSource(config);
    }
}
