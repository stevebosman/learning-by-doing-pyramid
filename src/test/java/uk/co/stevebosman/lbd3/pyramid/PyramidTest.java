package uk.co.stevebosman.lbd3.pyramid;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PyramidTest {

  @Test
  void create() {
    assertDoesNotThrow(() -> {
      new Pyramid("src/test/resources/pyramid-1-plane.txt");
    });
  }

  @ParameterizedTest
  @CsvSource({
          "src/test/resources/pyramid-1-plane.txt,1",
          "src/test/resources/pyramid-2-planes.txt,4",
          "src/test/resources/pyramid-3-planes.txt,5",
          "src/test/resources/example2.txt,46",
  })
  void maximal(final String filename, final long expected) throws IOException {
    assertEquals(expected, new Pyramid(filename).maximal());
  }
}