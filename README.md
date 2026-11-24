# Hashing

String hash functions: `polynomialHash`, the polynomial hash computed with Horner's loop for any base; `javaHash`, the base-31 case, which gives the same value as Java's `String.hashCode`; and two other well-known hashes, `djb2` and `fnv1a`. A JUnit suite checks each hash against known values, including the ones the notes trace by hand.

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
      demo/                             # one demo per class you implement
        StringHashDemo.java
    test/
      hashing/
        StringHashTest.java             # known values for each hash
  scripts/
    run.sh                              # compile and run one demo
    test.sh                             # compile and run the JUnit tests
```

## How to compile and run

- `scripts/test.sh` — compiles everything and runs the full JUnit suite.
- `scripts/test.sh hashing.StringHashTest` — compiles everything and runs only that test class.
- `scripts/run.sh demo.StringHashDemo` — compiles everything and runs that one demo. Every class you implement has its own demo, `<Class>Demo`.

## Contents

- `hashing.StringHash` — four string hash functions. Each one reads the string once and folds every character into one running `int`. They differ in their constants and in whether they mix a character in by adding or by XOR. The result is an `int`, so it can be negative.
- `hashing.StringHashTest` — checks the traced values (`"CAB"` is `66468` and `"ABC"` is `64578`), checks that `javaHash` agrees with `String.hashCode`, checks the hash codes of the keys the notes store in their first table, and checks `djb2` and `fnv1a` against exact values, including the published FNV test vectors.
- `demo.StringHashDemo` — hashes `"cat"`, `"dog"`, and `"cow"` with the base-31 polynomial hash, `"cat"` with `javaHash`, the anagrams `"CAB"` and `"ABC"`, and `"cat"` with DJB2 and FNV-1a.
