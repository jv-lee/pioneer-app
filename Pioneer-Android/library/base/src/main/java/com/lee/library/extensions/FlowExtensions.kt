package com.lee.library.extensions

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

typealias UiStateLiveData<T> = LiveData<UiState2<T>>
typealias UiStateMutableLiveData<T> = MutableLiveData<UiState2<T>>

@MainThread
inline fun <T> UiStateLiveData<T>.observeState(
    owner: LifecycleOwner,
    crossinline onLoading: () -> Unit = {},
    crossinline onSuccess: (T) -> Unit = {},
    crossinline onError: (Exception) -> Unit = {}
) {
    observe(owner) { state ->
        when (state) {
            is UiState2.Loading -> onLoading()
            is UiState2.Success -> onSuccess(state.data)
            is UiState2.Error -> onError(state.error)
        }
    }
}

sealed class UiState2<out T> {
    object Loading : UiState2<Nothing>()
    data class Success<out T>(val data: T) : UiState2<T>()
    data class Error(val error: Exception = RuntimeException()) : UiState2<Nothing>()
}