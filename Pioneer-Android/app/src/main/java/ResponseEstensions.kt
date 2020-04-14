import com.lee.library.mvvm.CustomException
import com.lee.pioneer.constants.HttpConstant.Companion.BASE_HTTP_SUCCESS
import com.lee.pioneer.model.entity.Data
import com.lee.pioneer.model.entity.PageData
import kotlinx.coroutines.coroutineScope

/**
 * @author jv.lee
 * @date 2020/3/26
 * @description
 */
suspend fun <T> executeResponseAny(response: T?, successBlock: (T) -> Unit) {
    coroutineScope {
        when (response) {
            null -> {
                throw Exception("response is null")
            }
            else -> {
                successBlock(response)
            }
        }
    }
}

suspend fun <T> executeResponse(
    response: Data<T>?,
    successBlock: (T) -> Unit
) {
    coroutineScope {
        when {
            response == null -> {
                throw Exception("response is null")
            }
            response.status != BASE_HTTP_SUCCESS -> {
                throw Exception("status not success")
            }
            else -> {
                successBlock(response.data)
            }
        }
    }
}

suspend fun <T> executeResponse(
    response: Data<T>?,
    successBlock: (T) -> Unit,
    failedBlock: (CustomException) -> Unit
) {
    coroutineScope {
        when {
            response == null -> {
                failedBlock(
                    CustomException(
                        -1,
                        Exception("request server not response , response == null.")
                    )
                )
            }
            response.status != BASE_HTTP_SUCCESS -> {
                failedBlock(
                    CustomException(
                        response.status,
                        Exception("request server failed status code $response.status")
                    )
                )
            }
            else -> {
                successBlock(response.data)
            }
        }
    }
}