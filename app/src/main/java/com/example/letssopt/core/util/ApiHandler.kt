package com.example.letssopt.core.util

import com.example.letssopt.data.network.RetrofitClient
import kotlinx.coroutines.CancellationException
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import retrofit2.Response

inline fun <T> safeRunCatching(block: () -> T): Result<T> {
    return try {
        Result.success(block())
    } catch (e: CancellationException) {
        throw e
    } catch (e: Exception) {
        Result.failure(e)
    }
}

fun <T> Response<T>.parseError(): String {
    val errorBody = errorBody()?.string()
    return if (!errorBody.isNullOrBlank()) {
        try {
            val jsonElement = RetrofitClient.json.parseToJsonElement(errorBody)
            jsonElement.jsonObject["message"]?.jsonPrimitive?.content ?: "오류 발생 (${code()})"
        } catch (e: Exception) {
            "오류 발생 (${code()}): ${message()}"
        }
    } else {
        "서버 응답 없음 (${code()})"
    }
}
