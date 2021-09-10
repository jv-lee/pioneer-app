package com.lee.library.extensions

import com.lee.library.mvvm.base.BaseLiveData
import com.lee.library.utils.LogUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

/**
 * @author jv.lee
 * @date 2020/11/26
 * @description
 */
suspend fun <T> Flow<T>.bindLive(liveData: BaseLiveData<T>) {
    catch {
        liveData.throwMessage(it)
    }.collect {
        liveData.value = it
    }
}

fun <T> Flow<T>.notNull(): Flow<T> {
    return this.filter { it != null }
}

fun <T> Flow<T>.flowOnIO(): Flow<T> {
    return this.flowOn(Dispatchers.IO)
}

fun <T> Flow<T>.flowOnMain(): Flow<T> {
    return this.flowOn(Dispatchers.Main)
}

fun <T> Flow<T>.uiState(): Flow<UiState> {
    return transform { value ->
        LogUtil.i("转换成功数据")
        return@transform emit(UiState.Success(value) as UiState)
    }.catch {
        LogUtil.i("转换失败数据")
        emit(UiState.Error(it))
    }
}

inline fun <reified T> UiState.call(
    crossinline success: (T) -> Unit,
    crossinline error: (Throwable) -> Unit,
    crossinline default: () -> Unit = {}
) {
    when (this) {
        is UiState.Success<*> -> success(this.data as T)
        is UiState.Error -> error(this.exception)
        is UiState.Default -> default()
    }
}

suspend inline fun <reified T> Flow<UiState>.collect(
    crossinline success: (T) -> Unit,
    crossinline error: (Throwable) -> Unit,
    crossinline default: () -> Unit = {}
) {
    collect {
        it.call(success, error, default)
    }
}

sealed class UiState {
    data class Success<T>(val data: T) : UiState()
    data class Error(val exception: Throwable) : UiState()
    object Default : UiState()
}