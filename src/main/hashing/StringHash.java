package hashing;

/** String hash functions. */
public final class StringHash {

  private StringHash() {
    // This class should not be instantiated!
  }

  // Assumes key is not null. Returns the polynomial hash of key in the given
  // base. The result may be negative.
  public static int polynomialHash(String key, int base) {
    // TODO: Implement me
    throw new UnsupportedOperationException("TODO: Implement me");
  }

  // Assumes key is not null. Returns the polynomial hash of key in base 31,
  // which equals key.hashCode(). The result may be negative.
  public static int javaHash(String key) {
    int h = 0;
    for (int i = 0; i < key.length(); i++) {
      h = 31 * h + key.charAt(i);
    }
    return h;
  }

  // Assumes key is not null. Returns the DJB2 hash of key: the polynomial
  // hash in base 33, starting from 5381 instead of 0. The result may be
  // negative.
  public static int djb2(String key) {
    int h = 5381;
    for (int i = 0; i < key.length(); i++) {
      h = 33 * h + key.charAt(i);
    }
    return h;
  }

  // Assumes key is not null. Returns the FNV-1a hash of key. The result may
  // be negative.
  public static int fnv1a(String key) {
    int h = 0x811c9dc5;  // a fixed starting seed
    for (int i = 0; i < key.length(); i++) {
      h = (h ^ key.charAt(i)) * 0x01000193;  // XOR the character in, then multiply
    }
    return h;
  }
}
