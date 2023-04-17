package com.freshtuna.sharp.id

import java.util.UUID

class PublicId(
    private val stringValue: String
) {

    constructor(id: UUID) : this(stringValue = id.toString())

    constructor(id: Long) : this(stringValue = id.toString())

    fun longId() = stringValue.toLong()

    fun stringId() = stringValue
    override fun toString() = stringValue

    override fun equals(other: Any?): Boolean {
        return toString() == other.toString()
    }
}
