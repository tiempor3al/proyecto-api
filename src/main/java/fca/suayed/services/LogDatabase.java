package fca.suayed.services;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.logging.Logger;

@ApplicationScoped
public class LogDatabase {

    private static final Logger LOG = Logger.getLogger(LogDatabase.class);

    @ConfigProperty(name = "quarkus.datasource.username")
    String username;

    @ConfigProperty(name = "quarkus.datasource.password")
    String password;

    @ConfigProperty(name = "quarkus.datasource.jdbc.url")
    String jdbcUrl;

    @PostConstruct
    void logDatabaseConfig() {
        LOG.info("Database configuration:");
        LOG.infof("JDBC URL: %s", jdbcUrl);
        LOG.infof("Username: %s", username);
        LOG.infof("Password: %s", password);
    }

    @Override
    public String toString() {
        return "ok";
    }
}
