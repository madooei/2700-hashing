# Hashing

String hash functions: `polynomialHash`, the polynomial hash computed with Horner's loop for any base; `javaHash`, the base-31 case, which gives the same value as Java's `String.hashCode`; and two other well-known hashes, `djb2` and `fnv1a`. A JUnit suite checks the values the notes trace by hand.

## Prerequisites

- JDK 17+ (JUnit 6 requires it).
- The JUnit jar is already vendored in `lib/`; there is nothing to download.

## Repository layout

```plaintext
code/
  README.md
  .gitignore
  lib/
    junit-platform-console-standalone-6.1.0.jar
  src/
    main/
      hashing/
        StringHash.java                 # polynomialHash, javaHash, djb2, fnv1a
        Main.java                       # demo entry point: hashes a few words
    test/
      hashing/
        StringHashTest.java             # the hash values the notes trace
  scripts/
    run.sh                              # compile and run the demo (hashing.Main)
    test.sh                             # compile and run the JUnit tests
```

## How to compile and run

- `scripts/test.sh` — compiles everything and runs the full JUnit suite.
- `scripts/test.sh hashing.StringHashTest` — compiles everything and runs only that test class.
- `scripts/run.sh` — compiles everything and runs the `Main` demo.

## What's here

- `hashing.StringHash` — four string hash functions. Each one reads the string once and folds every character into one running `int`. They differ in their constants and in whether they mix a character in by adding or by XOR. The result can be negative when the running total overflows.
- `hashing.Main` — hashes `"cat"`, `"dog"`, and `"cow"` with the base-31 polynomial hash, then the anagrams `"CAB"` and `"ABC"`, then `"cat"` with DJB2 and FNV-1a.
- `hashing.StringHashTest` — checks the traced values (`"CAB"` is `66468` and `"ABC"` is `64578`), checks that `javaHash` agrees with `String.hashCode`, and checks the hash codes of the keys the notes store in their first table.
