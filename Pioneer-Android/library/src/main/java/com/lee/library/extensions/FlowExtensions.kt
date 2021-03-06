package com.lee.library.extensions

import com.lee.library.mvvm.base.BaseLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

/**
 * @author jv.lee
 * @date 2020/11/26
 * @description
 */
suspend fun <T> Flow<T>.bindLive(liveData: BaseLiveData<T>) {
    Dispatchers
    catch {
        liveData.throwMessage(it)
    }.collect {
        liveData.value = it
    }
}

fun <T> Flow<T>.notNull(): Flow<T> {
    return this.filter { it != null }
}

fun <T> Flow<T>.dispatchersIO(): Flow<T> {
    return this.flowOn(Dispatchers.IO)
}