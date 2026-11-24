package demo;

import hashing.StringHash;

/** A demo of StringHash on a few sample words and the anagrams the notes trace. */
public final class StringHashDemo {

  private StringHashDemo() {
    // This class should not be instantiated!
  }

  public static void main(String[] args) {
    String[] words = {"cat", "dog", "cow"};

    // Expect cat -> 98262, dog -> 99644, cow -> 98699.
    System.out.println("Polynomial hash (base 31):");
    for (int i = 0; i < words.length; i++) {
      String word = words[i];
      System.out.println(word + " -> " + StringHash.polynomialHash(word, 31));
    }

    // javaHash is the base-31 case. Expect the same 98262 for "cat".
    System.out.println();
    System.out.println("javaHash on the word \"cat\":");
    System.out.println("cat -> " + StringHash.javaHash("cat"));

    // The anagrams get different values. Expect CAB -> 66468 and ABC -> 64578.
    System.out.println();
    System.out.println("The two anagrams the notes trace:");
    System.out.println("CAB -> " + StringHash.polynomialHash("CAB", 31));
    System.out.println("ABC -> " + StringHash.polynomialHash("ABC", 31));

    // Expect djb2 -> 193488125 and fnv1a -> 108289031.
    System.out.println();
    System.out.println("Other hashes on the word \"cat\":");
    System.out.println("djb2  -> " + StringHash.djb2("cat"));
    System.out.println("fnv1a -> " + StringHash.fnv1a("cat"));
  }
}
