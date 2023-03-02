package com.test;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.lifecycle.Startables;
import org.testcontainers.utility.DockerImageName;

public abstract class TestContainersInit {

  public static PostgreSQLContainer<?> postgreSQLContainer;
  public static Network network = Network.newNetwork();

  static {
    postgreSQLContainer =
        new PostgreSQLContainer<>(DockerImageName.parse("postgres:9.6.18-alpine"))
            .withDatabaseName("test_database")
            .withUsername("test_user")
            .withPassword("test_password")
            .withNetwork(network);
  }

  public static void startContainer() {
    if (!postgreSQLContainer.isRunning()) {
      postgreSQLContainer.start();
      Startables.deepStart(postgreSQLContainer).join();
    }
  }

  public static void stopContainer() {
    if (postgreSQLContainer.isRunning()) {
      postgreSQLContainer.stop();
    }
  }

  @DynamicPropertySource
  public static void setDynamicProperties(DynamicPropertyRegistry registry) {
    registry.add(
        "spring.r2dbc.url",
        () ->
            "r2dbc:postgresql://127.0.0.1:"
                + postgreSQLContainer.getFirstMappedPort()
                + "/"
                + postgreSQLContainer.getDatabaseName());
    registry.add("spring.r2dbc.username", postgreSQLContainer::getUsername);
    registry.add("spring.r2dbc.password", postgreSQLContainer::getPassword);
  }
}
