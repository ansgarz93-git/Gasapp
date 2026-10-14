package dev.ansgarz.fieldkit

/**
 * Copies a map-like record while excluding fields that must be re-entered.
 * The function is intentionally transport-, UI- and domain-agnostic.
 */
object CopyWithExclusions {
    fun <V> copy(source: Map<String, V>, excludedKeys: Set<String>): Map<String, V> =
        source.filterKeys { it !in excludedKeys }.toMap()
}
