import com.lee.pioneer.const.HttpConstant.Companion.BASE_HTTP_SUCCESS
import com.lee.pioneer.model.Data

/**
 * @author jv.lee
 * @date 2020/3/26
 * @description
 */
suspend fun <T> call(data: Data<T>, successBlock: (List<T>) -> Unit, failedBlock: () -> Unit) {
    if (data.status == BASE_HTTP_SUCCESS) {
        successBlock(data.data)
    } else {
        failedBlock()
    }
}