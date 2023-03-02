package com.test.repo;


import com.test.model.BookingDto;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends R2dbcRepository<BookingDto, String> {
}
