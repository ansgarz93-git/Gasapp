# Public Threat Model

This threat model covers only the code and build artifacts in this public repository. It intentionally contains no assumptions or information about non-public products, deployments, operators, networks, credentials, or datasets.

## Security goals

The library should preserve:

1. **Integrity of calculations** — technical values, recurring dates, due-state classification, workflow ordering, and copy exclusions should behave deterministically.
2. **Predictable validation** — malformed or unsupported caller input should fail explicitly rather than be silently reinterpreted.
3. **Minimal attack surface** — the core library should remain free of networking, persistence, authentication, telemetry, and runtime code loading.
4. **Supply-chain clarity** — dependencies and build changes should remain small enough to review and test.
5. **Boundary preservation** — no private-system implementation details or operational data should enter the public repository.

## Assets

The relevant assets are:

- correctness of public API behavior;
- integrity of source, tests, build configuration, and release artifacts;
- maintainers' ability to review changes reproducibly;
- consumers' ability to distinguish library behavior from their own application policy.

This repository does not contain production credentials, private endpoints, operational datasets, or a backend service.

## Trust boundaries

### Caller input → library

Strings, dates, interval values, collection indexes, and exclusion keys are caller-controlled. The library must validate structural preconditions that are part of its public API. Domain policy that belongs to a consuming application remains outside this boundary.

### Repository changes → build output

Contributions, dependency changes, CI configuration, and build scripts can alter shipped behavior. Changes should be reviewable, tested, and kept narrowly scoped.

### Library → consuming application

The library returns deterministic values but does not authorize actions or certify equipment, inspections, or operational safety. Consumers are responsible for applicable engineering rules, regulations, authorization, persistence, synchronization, and human review.

## Primary threat scenarios

### Incorrect deadline classification

A date-boundary defect could cause a consuming application to present a recurring inspection as later or earlier than intended.

**Controls:** boundary-focused unit tests, `LocalDate` semantics, explicit `DUE_TODAY` handling, configurable warning windows, and deterministic calculations.

### Malformed technical values

Blank or structurally invalid values could propagate into downstream records if accepted silently.

**Controls:** explicit validation at public API boundaries and tests for rejected input.

### Workflow/index misuse

Invalid indexes or unexpected ordering operations could corrupt an ordered workflow representation.

**Controls:** immutable transformations and explicit index preconditions.

### Unsafe record copying

A consumer could accidentally copy identity-like fields when creating a related record.

**Controls:** explicit exclusion sets. The caller remains responsible for choosing the correct excluded fields for its domain.

### Dependency or build-pipeline compromise

A compromised dependency or workflow action could affect build integrity.

**Controls:** intentionally small dependency surface, reviewed build-file changes, CI tests, and least-privilege workflow permissions (`contents: read`). Pinning or additional provenance controls may be added as the release process matures.

### Sensitive-information leakage through contributions

A contribution could accidentally include credentials, private endpoints, operational records, proprietary datasets, or implementation details from a non-public system.

**Controls:** documented repository boundary, contribution rules, maintainer review, and explicit rejection of private-system material.

## Out of scope

The following are intentionally outside this repository's threat model:

- authentication and authorization;
- server or database security;
- network transport and endpoint security;
- device provisioning or trust establishment;
- credential, key, or certificate management;
- production deployment and backup procedures;
- operator-specific workflows or datasets;
- regulatory certification of a consuming application or physical system.

Their absence is a deliberate security boundary, not an assertion that those concerns are unimportant.

## Security review checklist

For security-sensitive changes, reviewers should ask:

- Does this introduce I/O, networking, persistence, dynamic loading, or telemetry?
- Does it add a dependency or expand dependency privileges?
- Are new inputs validated explicitly?
- Are boundary and failure cases covered by tests?
- Could the change cause silent reinterpretation of maintenance or technical data?
- Does it expose or imply details from a non-public system?
- Can the same goal be achieved with a smaller public API or dependency surface?

## Review cadence

This document should be revisited when the public API gains a new trust boundary, external dependency, build/release mechanism, or I/O capability.
