# Release process

The release process is intentionally small and reviewable.

## Before release

1. Ensure the planned version is recorded in `build.gradle.kts`.
2. Move the relevant changelog entries from `Unreleased` into a dated version section.
3. Confirm all intended changes reached `main` through reviewed pull requests.
4. Confirm CI is green on the release candidate.
5. Review `SECURITY.md`, `docs/THREAT_MODEL.md`, and `docs/SUPPLY_CHAIN.md` for any trust-boundary changes.
6. Confirm no credentials, private endpoints, operational data, proprietary datasets, or private-system implementation details were added.

## Release

Create an immutable version tag matching the library version, for example `v0.1.1`, from the reviewed `main` commit. Release notes should summarize the corresponding changelog section and link security-relevant changes where appropriate.

## After release

1. Verify the tag points to the intended reviewed commit.
2. Verify CI remains green on `main`.
3. Open follow-up issues for known hardening work instead of hiding known limitations.
4. Keep dependency and action updates flowing through normal pull-request review.

This process applies only to this public repository and does not describe any private product or deployment process.
