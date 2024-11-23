package uk.co.stevebosman.lbd3;

import uk.co.stevebosman.lbd3.pyramid.Pyramid;
import uk.co.stevebosman.lbd3.triangle.Triangle;

import java.io.IOException;

public class Main {
  public static void main(final String[] args) throws IOException {
    if (args.length != 2) {
      System.err.println("Usage: Main [triangle|pyramid] filename");
    } else if ("triangle".equals(args[0])) {
      System.out.println(new Triangle(args[1]).maximal());
    } else if ("pyramid".equals(args[0])) {
      System.out.println(new Pyramid(args[1]).maximal());
    } else {
      System.err.println("Unknown type: " + args[0]);
    }
  }
}