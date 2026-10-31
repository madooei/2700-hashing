package hashing;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Tests for StringHash. The expected values are the ones the notes trace by
 * hand or state.
 */
public class StringHashTest {

  // The notes trace the base-31 loop on "CAB": 0 -> 67 -> 2142 -> 66468.
  @Test
  public void polynomialHashOfCab() {
    assertEquals(66468, StringHash.polynomialHash("CAB", 31));
  }

  // The notes trace the base-31 loop on "ABC": 0 -> 65 -> 2081 -> 64578.
  @Test
  public void polynomialHashOfAbc() {
    assertEquals(64578, StringHash.polynomialHash("ABC", 31));
  }

  @Test
  public void anagramsHashDifferently() {
    assertNotEquals(
        StringHash.polynomialHash("CAB", 31),
        StringHash.polynomialHash("ABC", 31));
  }

  @Test
  public void polynomialHashWithBaseOneIsTheCharacterSum() {
    assertEquals(67 + 65 + 66, StringHash.polynomialHash("CAB", 1));
  }

  @Test
  public void javaHashOfCab() {
    assertEquals(66468, StringHash.javaHash("CAB"));
  }

  @Test
  public void javaHashIsPolynomialHashWithBase31() {
    assertEquals(
        StringHash.polynomialHash("banana", 31),
        StringHash.javaHash("banana"));
  }

  @Test
  public void javaHashMatchesStringHashCode() {
    assertEquals("hello".hashCode(), StringHash.javaHash("hello"));
  }

  @Test
  public void javaHashMatchesStringHashCodeWhenItOverflows() {
    assertEquals("a much longer key".hashCode(), StringHash.javaHash("a much longer key"));
  }

  @Test
  public void polynomialHashIsDeterministic() {
    assertEquals(
        StringHash.polynomialHash("banana", 31),
        StringHash.polynomialHash("banana", 31));
  }

  @Test
  public void djb2IsDeterministic() {
    assertEquals(StringHash.djb2("banana"), StringHash.djb2("banana"));
  }

  @Test
  public void fnv1aIsDeterministic() {
    assertEquals(StringHash.fnv1a("banana"), StringHash.fnv1a("banana"));
  }

  @Test
  public void djb2TellsCatFromDog() {
    assertNotEquals(StringHash.djb2("cat"), StringHash.djb2("dog"));
  }

  @Test
  public void fnv1aTellsCatFromDog() {
    assertNotEquals(StringHash.fnv1a("cat"), StringHash.fnv1a("dog"));
  }

  // The first table in the notes uses these four keys. Their hash codes
  // compress to slots 6, 4, 3, and 2 of a table of 8 slots.
  @Test
  public void javaHashOfCat() {
    assertEquals(98262, StringHash.javaHash("cat"));
  }

  @Test
  public void javaHashOfDog() {
    assertEquals(99644, StringHash.javaHash("dog"));
  }

  @Test
  public void javaHashOfCow() {
    assertEquals(98699, StringHash.javaHash("cow"));
  }

  @Test
  public void javaHashOfBee() {
    assertEquals(97410, StringHash.javaHash("bee"));
  }

  // The collisions page: "pig" compresses to slot 6, the slot "cat" is in.
  @Test
  public void javaHashOfPig() {
    assertEquals(110990, StringHash.javaHash("pig"));
  }
}
