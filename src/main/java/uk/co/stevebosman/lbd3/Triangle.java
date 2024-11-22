package uk.co.stevebosman.lbd3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Triangle {
  private final List<List<Long>> rows;

  public Triangle(final String filename) throws IOException {
    final List<String> lines = Files.readAllLines(Path.of(filename));
    this.rows = lines.stream()
                     .map(Triangle::convertLineToList)
                     .toList();

  }

  private static List<Long> convertLineToList(final String line) {
    return Arrays.stream(line.split("\\s+"))
                 .map(s -> Long.parseLong(s, 16))
                 .toList();
  }

  public long maximal() {
    final Map<TriangleCoordinate, Long> values = new HashMap<>();
    long max = 0;
    for (int r = rows.size() - 1; r >= 0; r--) {
      final List<Long> row = rows.get(r);
      long maxForRow = 0;
      for (int c = 0; c < row.size(); c++) {
        long value = row.get(c);
        if (r != rows.size() - 1) {
          value += Math.max(values.get(new TriangleCoordinate(r+1, c)), values.get(new TriangleCoordinate(r+1, c + 1)));
        };
        values.put(new TriangleCoordinate(r, c), value);
        maxForRow = Math.max(maxForRow, value);
      }
      if (r == 0) max = maxForRow;
    }
    return max;
  }
}
