package uk.co.stevebosman.lbd3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class Pyramid {
  private final List<Plane<Long>> planes = new ArrayList<>();

  public Pyramid(final String filename) throws IOException {
    final List<String> lines = Files.readAllLines(Path.of(filename));
    List<List<Long>> currentPlane = new ArrayList<>();
    for (final String line : lines) {
      currentPlane.add(convertLineToList(line));
      if (currentPlane.size() == planes.size() + 1) {
        planes.add(new Plane<>(Collections.unmodifiableList(currentPlane)));
        currentPlane = new ArrayList<>();
      }
    }
  }

  private static List<Long> convertLineToList(final String line) {
    return Arrays.stream(line.split("\\s+"))
                 .map(s -> Long.parseLong(s, 16))
                 .toList();
  }

  public long maximal() {
    final Map<PyramidCoordinate, Long> values = new HashMap<>();
    long max = 0;
    for (int p = planes.size() - 1; p >= 0; p--) {
      final Plane<Long> plane = planes.get(p);
      long maxForPlane = 0;
      for (int x = 0; x < p + 1; x++) {
        for (int y = 0; y < p + 1; y++) {
          long value = plane.get(x, y);
          if (p != planes.size() - 1) {
            value += Stream.of(values.get(new PyramidCoordinate(p + 1, x, y)),
                               values.get(new PyramidCoordinate(p + 1, x + 1, y)),
                               values.get(new PyramidCoordinate(p + 1, x, y + 1)),
                               values.get(new PyramidCoordinate(p + 1, x + 1, y + 1))
                           )
                           .max(Long::compare)
                           .orElse(0L);
          }
          values.put(new PyramidCoordinate(p, x, y), value);
          maxForPlane = Math.max(maxForPlane, value);
        }
      }
      if (p == 0) max = maxForPlane;
    }
    return max;
  }
}
