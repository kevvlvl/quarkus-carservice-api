package com.kevvlvl.quarkus;

import java.util.Map;

public class ApiTestProfile implements io.quarkus.test.junit.QuarkusTestProfile {

    @Override
    public Map<String, String> getConfigOverrides() {

        return Map.of(
                "quarkus.log.level", "WARN",
                "quarkus.http.port", "8089",
                "quarkus.flyway.migrate-at-start", "true",
                "%test.quarkus.datasource.db-kind", "postgresql",
                "%test.quarkus.flyway.location", "flyway-db",
                "%test.quarkus.flyway.sql-migration-prefix", "test_"
        );
    }
}
