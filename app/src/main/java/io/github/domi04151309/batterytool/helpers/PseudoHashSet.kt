package io.github.domi04151309.batterytool.helpers

class PseudoHashSet : HashSet<String>() {
    @Suppress("SameReturnValue")
    override fun contains(element: String): Boolean = false

    companion object {
        @java.io.Serial
        private const val serialVersionUID = 1L
    }
}