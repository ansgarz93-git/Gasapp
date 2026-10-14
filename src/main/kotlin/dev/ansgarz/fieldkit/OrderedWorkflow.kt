package dev.ansgarz.fieldkit

/** Immutable helper for user-editable ordered process/component sequences. */
data class OrderedWorkflow<T>(val items: List<T> = emptyList()) {
    fun add(item: T, index: Int = items.size): OrderedWorkflow<T> {
        require(index in 0..items.size) { "Index out of bounds: $index" }
        return OrderedWorkflow(items.toMutableList().apply { add(index, item) })
    }

    fun removeAt(index: Int): OrderedWorkflow<T> {
        require(index in items.indices) { "Index out of bounds: $index" }
        return OrderedWorkflow(items.toMutableList().apply { removeAt(index) })
    }

    fun move(from: Int, to: Int): OrderedWorkflow<T> {
        require(from in items.indices) { "Source index out of bounds: $from" }
        require(to in items.indices) { "Target index out of bounds: $to" }
        if (from == to) return this
        val copy = items.toMutableList()
        val value = copy.removeAt(from)
        copy.add(to, value)
        return OrderedWorkflow(copy)
    }
}
