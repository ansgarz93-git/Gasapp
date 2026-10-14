# Industrial Field Toolkit

A small Kotlin/JVM library for reusable, domain-neutral building blocks used by industrial field-service applications.

The project intentionally focuses on non-sensitive client-side primitives:

- technical unit/value selections such as DN/inch and PN/Class;
- recurring inspection interval calculation;
- immutable, user-editable ordered workflows;
- record copying with explicit field exclusions.

## Scope and security boundary

This repository is deliberately standalone. It contains no private product source, production networking or backend implementation, credentials, operational datasets, or deployment configuration.

The code is designed to be useful on its own and does not document any private system.

## Example

```kotlin
val diameter = TechnicalUnits.diameter(DiameterUnit.DN, "80")
val due = InspectionInterval(12, IntervalUnit.MONTHS)
    .nextAfter(LocalDate.of(2026, 10, 14))

val workflow = OrderedWorkflow(listOf("filter", "regulator"))
    .add("meter", index = 1)

val safeCopy = CopyWithExclusions.copy(
    source = mapOf("type" to "demo", "serial" to "123"),
    excludedKeys = setOf("serial")
)
```

## Build

Requires JDK 17+ and Gradle 8.x.

```bash
gradle test
```

A dependency-free smoke test can also be compiled with `kotlinc`.

## Status

Early public API (`0.1.0`). The scope is intentionally narrow. Contributions that keep the library generic, testable and independent from proprietary systems are welcome.

## License

Apache License 2.0. See [LICENSE](LICENSE).
