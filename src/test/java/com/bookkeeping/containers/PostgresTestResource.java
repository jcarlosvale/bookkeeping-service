package com.bookkeeping.containers;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.HashMap;
import java.util.Map;

public class PostgresTestResource implements QuarkusTestResourceLifecycleManager {

    private static final PostgreSQLContainer<?> postgres =
            new PostgreSQLContainer<>(DockerImageName.parse("postgres:15-alpine"))
                    .withDatabaseName("bookkeeping_test")
                    .withUsername("postgres")
                    .withPassword("postgres");

    @Override
    public Map<String, String> start() {
        postgres.start();

        Map<String, String> config = new HashMap<>();
        config.put("quarkus.datasource.db-kind", "postgresql");
        config.put("quarkus.datasource.username", postgres.getUsername());
        config.put("quarkus.datasource.password", postgres.getPassword());
        config.put("quarkus.datasource.reactive.url", String.format(
                "postgresql://%s:%d/%s",
                postgres.getHost(),
                postgres.getMappedPort(5432),
                postgres.getDatabaseName()
        ));
        config.put("quarkus.datasource.jdbc.url", String.format(
                "jdbc:postgresql://%s:%d/%s",
                postgres.getHost(),
                postgres.getMappedPort(5432),
                postgres.getDatabaseName()
        ));

        config.put("quarkus.datasource.devservices.enabled", "false");
        config.put("quarkus.hibernate-orm.log.sql", "true");
        config.put("quarkus.flyway.enabled", "true");
        config.put("quarkus.flyway.migrate-at-start", "true");
        config.put("quarkus.hibernate-orm.database.generation", "none");

        return config;
    }

    @Override
    public void stop() {
        postgres.stop();
    }
}
