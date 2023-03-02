package com.test;

import com.test.controller.TicketBookingController;
import com.test.model.BookingDto;
import com.test.repo.BookingRepository;
import com.test.utils.UniqueIDGenerator;
import java.util.UUID;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@SpringBootTest
class TicketBookingControllerTest {

  private WebTestClient webTestClient;

  @Autowired private TicketBookingController bookingController;

  @Autowired private BookingRepository bookingRepository;

  @MockBean private UniqueIDGenerator idGenerator;

  private String id;

  @BeforeEach
  public void init() {
    webTestClient = WebTestClient.bindToController(bookingController).build();
  }

  @BeforeAll
  public static void setUp() {
    TestContainersInit.startContainer();
  }

  @AfterAll
  public static void stop() {
    TestContainersInit.stopContainer();
  }

  @Test
  void testSave() {
    id = UUID.randomUUID().toString();
    BookingDto bookingRequest = BookingDto.builder().build();
    Mockito.when(idGenerator.generate()).thenReturn(id);
    Mono<BookingDto> bookingRequestMono = Mono.just(bookingRequest);
    webTestClient
        .post()
        .uri("/api/book")
        .body(Mono.just(bookingRequestMono), BookingDto.class)
        .exchange()
        .expectStatus()
        .isCreated();
  }

  @Test
  void getTest() {
    webTestClient.get().uri("/api/book/" + id).exchange().expectStatus().isOk();
  }
}
