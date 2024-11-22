package uk.co.stevebosman.lbd3;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class TriangleTest {

  @Test
  void create() {
    assertDoesNotThrow(() -> {
      new Triangle("src/test/resources/triangle-1-row.txt");
    });
  }

  @ParameterizedTest
  @CsvSource({
          "src/test/resources/triangle-1-row.txt,1",
          "src/test/resources/triangle-2-rows.txt,3",
          "src/test/resources/triangle-3-rows.txt,5",
          "src/test/resources/example1.txt,23",
  })
  void maximal(final String filename, final long expected) throws IOException {
    assertEquals(expected, new Triangle(filename).maximal());
  }
}