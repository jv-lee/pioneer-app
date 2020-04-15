import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.lee.library.adapter.LeeViewAdapter
import com.lee.pioneer.model.entity.PageData

/**
 * @author jv.lee
 * @date 2020/3/30
 * @description 分页数据公共操作扩展执行函数
 */

/**
 * 分页数据列表错误处理
 */
fun <T> executePageError(
    adapter: LeeViewAdapter<T>,
    refreshView: SwipeRefreshLayout?
) {
    refreshView?.isRefreshing = false
    if (adapter.isPageCompleted && adapter.data.isNotEmpty()) {
        adapter.loadFailed()
    } else if (!adapter.isPageCompleted && adapter.data.isEmpty()) {
        adapter.pageError()
    }
}

/**
 * 分页数据列表填充处理
 */
fun <T> executePageCompleted(
    data: PageData<T>,
    adapter: LeeViewAdapter<T>,
    refreshView: SwipeRefreshLayout?,
    refreshBlock: () -> Unit = {},
    emptyBlock: () -> Unit = {}
) {
    refreshView?.isRefreshing = false
    if (data.page == 1) {
        if (data.data.isNullOrEmpty()) {
            adapter.pageEmpty()
            emptyBlock()
            return
        }
        adapter.updateData(data.data)
        adapter.notifyDataSetChanged()
        adapter.pageCompleted()
        refreshBlock()
    } else {
        adapter.addData(data.data)
        if (data.page >= data.page_count) {
            adapter.loadMoreEnd()
        } else {
            adapter.loadMoreCompleted()
        }
    }
}

fun <T> executePageCompleted(
    data: PageData<T>,
    adapter: LeeViewAdapter<T>,
    refreshBlock: () -> Unit = {},
    emptyBlock: () -> Unit = {}
) {
    executePageCompleted(data, adapter, null, refreshBlock, emptyBlock)
}