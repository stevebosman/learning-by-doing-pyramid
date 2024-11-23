package uk.co.stevebosman.lbd3.util;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LineParserTest {
  @ParameterizedTest
  @CsvSource({
          "1 2 3,1,2,3",
          "a ff ffff,10,255,65535"
  })
  void convertLineToList(final String line, final Long value1,  final Long value2,  final Long value3) {
    final List<Long> actual = LineParser.convertLineToList(line);
    final List<Long> expected = List.of(value1, value2, value3);
    assertIterableEquals(expected, actual);
  }
}
