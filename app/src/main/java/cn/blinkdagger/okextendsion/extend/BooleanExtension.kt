package cn.blinkdagger.okextendsion.extend


sealed class BooleanExt<out T>

object Otherwise : BooleanExt<Nothing>()
class WithData<T>(val data: T) : BooleanExt<T>()

inline fun <T> BooleanExt<T>.elseIf(predicate: Boolean, block: () -> T): BooleanExt<T> =
        when (this) {
            is Otherwise -> if (predicate) WithData(block()) else Otherwise
            is WithData -> this
        }

inline fun <T> Boolean.yes(block: () -> T) =
        when {
            this -> WithData(block())
            else -> Otherwise
        }


inline fun <T> BooleanExt<T>.otherwise(block: () -> T): T =
        when (this) {
            is Otherwise -> block()
            is WithData -> this.data
        }