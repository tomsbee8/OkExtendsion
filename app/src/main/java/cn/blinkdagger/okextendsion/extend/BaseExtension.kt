package cn.blinkdagger.okextendsion.extend

import android.net.Uri


inline fun ktTry(method: () -> Unit) {
    try {
        method()
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

inline fun <T1, T2> ifNotNull(value1: T1?, value2: T2?, bothNotNull: (t1: T1, t2: T2) -> Unit) {
    if (value1 != null && value2 != null) {
        bothNotNull(value1, value2)
    }
}

inline fun <T1, T2, T3> ifNotNull(value1: T1?, value2: T2?, value3: T3?, bothNotNull: (t1: T1, t2: T2, t3: T3) -> Unit) {
    if (value1 != null && value2 != null && value3 != null) {
        bothNotNull(value1, value2, value3)
    }
}

inline fun ifNotNullOrBlank(value1: String?, value2: String?, bothNotNullOrBlank: (t1: String, t2: String) -> Unit) {
    if (!value1.isNullOrBlank() && !value2.isNullOrBlank()) {
        bothNotNullOrBlank(value1, value2)
    }
}

inline fun ifNotNullOrBlank(value1: String?, value2: String?, value3: String?, bothNotNullOrBlank: (t1: String, t2: String, t3: String) -> Unit) {
    if (!value1.isNullOrBlank() && !value2.isNullOrBlank() && !value3.isNullOrBlank()) {
        bothNotNullOrBlank(value1, value2, value3)
    }
}

fun findFirstNotEmptyString(vararg params: String?): String? {
    return params.first { value ->
        !value.isNullOrEmpty()
    }
}

inline fun String?.ifNotEmpty(block: (String) -> Unit) {
    if (!this.isNullOrEmpty()) block(this)
}

inline fun String?.ifNotBlank(block: (String) -> Unit) {
    if (!this.isNullOrBlank()) block(this)
}

/**
 * url拼接参数
 */
fun String?.appendParams(params: String?): String {
    return params?.takeIf { it.isNotBlank() }?.let {
        if (this.orEmpty().contains('?')) "${this.orEmpty()}&$params" else "${this.orEmpty()}?$params"
    } ?: this.orEmpty()
}

/**
 * 获取url的params并且拼接参数
 */
fun String?.obtainParams(missingAppendParams: String = ""): String {
    return this?.run {
        val index = indexOf('?')
        return if (index == -1) missingAppendParams else "${substring(index + 1, length)}&$missingAppendParams"
    } ?: missingAppendParams
}

/**
 * 截取url "?"后的参数
 */
fun String?.subStringParams(): String {
    return this?.let {
        if (indexOf('?') > 0) {
            substring(indexOf('?'))
        } else ""
    } ?: ""
}


/**
 * 获取url中的某个参数
 */
fun String?.getParameterValue(key : String): String? {
    return Uri.parse(this.orEmpty())?.getQueryParameter(key)
}







