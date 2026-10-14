# Design

The toolkit follows four constraints:

1. **Domain neutrality** — APIs model reusable mechanics rather than a specific plant, company, or product.
2. **Client-side only** — no networking, authentication, persistence, provisioning, or server code.
3. **Explicit data minimization** — copying is allow-by-presence and supports explicit exclusions for identity-like fields.
4. **Small dependency surface** — production code uses only the Kotlin/JDK standard library.

The library is not a reference architecture for any private application. Similarities to private products are intentionally limited to generic software patterns.
