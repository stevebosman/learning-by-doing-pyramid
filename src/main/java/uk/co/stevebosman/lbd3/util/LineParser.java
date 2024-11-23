package uk.co.stevebosman.lbd3.util;

import java.util.Arrays;
import java.util.List;

public class LineParser {
  public static List<Long> convertLineToList(final String line) {
    return Arrays.stream(line.split("\\s+"))
                 .map(s -> Long.parseLong(s, 16))
                 .toList();
  }
}
