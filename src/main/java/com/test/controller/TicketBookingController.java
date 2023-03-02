package com.test.controller;

import com.test.model.BookingDto;
import com.test.model.BookingResponseDto;
import com.test.repo.BookingRepository;
import com.test.utils.UniqueIDGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TicketBookingController {

  private final BookingRepository bookingRepository;

  private final UniqueIDGenerator uniqueIDGenerator;

  @PostMapping("/book")
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<BookingResponseDto> book(@RequestBody BookingDto bookingDto) {

    bookingDto.setId(uniqueIDGenerator.generate());
    return bookingRepository
        .save(bookingDto.setAsNew())
        .map(
            entity ->
                BookingResponseDto.builder()
                    .pnrNumber(entity.getId())
                    .date(entity.getDate())
                    .build());
  }

  @GetMapping("/book/{id}")
  public Mono<BookingDto> book(@PathVariable String id) {
    return bookingRepository.findById(id);
  }
}
