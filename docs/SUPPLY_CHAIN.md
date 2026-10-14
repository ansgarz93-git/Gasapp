# Supply-chain policy

This document covers only this public repository and its build pipeline.

## GitHub Actions

External actions are pinned to reviewed immutable commit SHAs. A human-readable release comment is kept beside each pin so maintainers can understand the intended upstream release.

Current pins:

- `actions/checkout` — v7 — `3d3c42e5aac5ba805825da76410c181273ba90b1`
- `actions/setup-java` — v6 — `b843ebf9b913146b38da2eaa7aeddf76074ae1ad`
- `gradle/actions/setup-gradle` — v6.4.0 — `3f5f9adaf7d9fecd50b5935e54106014257a94e6`

Updates should be proposed through pull requests, reviewed against the upstream release/tag, and merged only after CI passes.

## Workflow permissions

CI uses repository read access only (`contents: read`). New permissions require explicit justification and security review.

## Dependency updates

Dependabot checks GitHub Actions and Gradle dependencies weekly. Updates remain normal pull requests and are not auto-merged.

The library has no runtime dependency beyond the Kotlin/JVM standard environment. Test/build dependencies are intentionally kept small and versioned explicitly.

## Gradle integrity

Gradle dependency verification is a planned hardening step. Until verification metadata is committed, dependency changes require explicit review of coordinates, versions, and repository source before merge.

## Review checklist

For every build or dependency change:

1. verify the upstream project and release source;
2. prefer immutable revisions for executable CI dependencies;
3. inspect requested workflow permission changes;
4. avoid adding runtime dependencies unless they are necessary for the public API;
5. run the full test suite in CI;
6. update this document when trust assumptions change.

This policy does not describe or imply any non-public deployment, product, network, credential, or operational environment.
