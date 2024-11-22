package uk.co.stevebosman.lbd3;

import java.util.List;

public record Plane<T>(List<List<T>> values) {
  public T get(final int x, final int y) {
    return values.get(x).get(y);
  }
}
