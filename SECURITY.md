# Security Policy

## Supported versions

The latest public release line is supported. Security-relevant fixes should target the current public API unless a specific older version is explicitly documented as supported.

## Reporting

Do not open a public issue if a report contains credentials, private endpoints, personal data, or information about a non-public system. Contact the maintainer privately through the GitHub profile instead.

Reports about this public library should include, where possible:

- affected version or commit;
- minimal reproduction steps;
- expected and observed behavior;
- security impact;
- whether the issue is already public elsewhere.

## Threat model

The repository's public security assumptions, trust boundaries, primary threat scenarios, and review checklist are documented in [docs/THREAT_MODEL.md](docs/THREAT_MODEL.md).

## Project boundary

This repository must remain independent from private production systems. Contributions must not add:

- credentials, tokens, certificates or keys;
- production URLs or private network details;
- authentication/provisioning implementations copied from another product;
- customer, user or installation data;
- proprietary datasets or non-redistributable catalogs;
- deployment or backend details from a private application.

Changes that introduce networking, persistence, runtime code loading, telemetry, or a new external dependency require explicit security review because they expand the current threat model.
