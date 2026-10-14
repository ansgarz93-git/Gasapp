# Industrial Field Toolkit

[![CI](https://github.com/ansgarz93-git/Gasapp/actions/workflows/ci.yml/badge.svg)](https://github.com/ansgarz93-git/Gasapp/actions/workflows/ci.yml)
[![License](https://img.shields.io/badge/license-Apache--2.0-blue.svg)](LICENSE)

A small Kotlin/JVM library for reusable, domain-neutral building blocks used by industrial field-service applications.

The project intentionally focuses on non-sensitive client-side primitives:

- technical unit/value selections such as DN/inch and PN/Class;
- recurring inspection interval and due-status calculation;
- immutable, user-editable ordered workflows;
- record copying with explicit field exclusions.

## Why this exists

Industrial maintenance and inspection software often repeats the same small pieces of logic around technical values, recurring checks and deterministic workflows. In safety-sensitive environments, inconsistent implementations can make maintenance data harder to review and reason about.

This toolkit keeps those primitives small, deterministic and independently testable. It is intended to support software used around industrial maintenance workflows, including critical-infrastructure environments, without embedding operator-specific data or proprietary system details.

It is a software building-block library, not a safety certification or a substitute for applicable engineering, regulatory or operational requirements.

## Scope and security boundary

This repository is deliberately standalone. It contains no private product source, production networking or backend implementation, credentials, operational datasets, or deployment configuration.

The code is designed to be useful on its own and does not document any private system. The public security assumptions and review boundaries are documented in [docs/THREAT_MODEL.md](docs/THREAT_MODEL.md).

## Example

```kotlin
val diameter = TechnicalUnits.diameter(DiameterUnit.DN, "80")
val interval = InspectionInterval(12, IntervalUnit.MONTHS)
val schedule = InspectionSchedule(interval, warningWindowDays = 30)

val due = schedule.dueDate(LocalDate.of(2025, 10, 14))
val status = schedule.status(
    lastCompleted = LocalDate.of(2025, 10, 14),
    referenceDate = LocalDate.of(2026, 10, 1),
)

val workflow = OrderedWorkflow(listOf("filter", "regulator"))
    .add("meter", index = 1)

val safeCopy = CopyWithExclusions.copy(
    source = mapOf("type" to "demo", "serial" to "123"),
    excludedKeys = setOf("serial")
)
```

## Maintenance

Changes are developed through tracked issues and pull requests. CI runs the test suite for every pull request and branch push. Security reports should follow [SECURITY.md](SECURITY.md), while normal changes should follow [CONTRIBUTING.md](CONTRIBUTING.md).

The public API is intentionally narrow: additions should remain generic, deterministic, testable and independent from proprietary systems.

## Build

Requires JDK 17+ and Gradle 8.x.

```bash
gradle test
```

A dependency-free smoke test can also be compiled with `kotlinc`.

## Status

Early public API (`0.1.x`). Contributions that preserve the project's narrow security boundary are welcome.

## License

Apache License 2.0. See [LICENSE](LICENSE).
