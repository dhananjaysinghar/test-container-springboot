package com.test.utils;

import java.util.Random;
import org.springframework.stereotype.Component;

@Component
public class UniqueIDGenerator {

  private static final long START_TIMESTAMP = 1104537600000L; // January 1, 2005 00:00:00 UTC
  // Current timestamp
  private static final long CURRENT_TIMESTAMP = System.currentTimeMillis();
  // Random number generator
  private static final Random RANDOM = new Random();
  // Generate a unique ID
  public String generate() {
    long timestamp = CURRENT_TIMESTAMP - START_TIMESTAMP;
    int randomNum = RANDOM.nextInt(900000) + 100000; // Generate a random 6-digit number
    String id = String.format("M%010d%d", timestamp, randomNum);
    return id;
  }
}
