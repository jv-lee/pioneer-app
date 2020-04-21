import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.lee.library.adapter.LeeViewAdapter
import com.lee.pioneer.model.entity.PageData

/**
 * @author jv.lee
 * @date 2020/3/30
 * @description TODO 分页数据状态展示 公共操作扩展执行函数
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
        //设置空页面
        if (data.data.isNullOrEmpty()) {
            adapter.pageEmpty()
            emptyBlock()
            return
        }
        //数据源相同不做任何操作
        if (adapter.data == data.data) {
            return
        }
        //正常情况第一页加载数据状态
        adapter.updateData(data.data)
        adapter.pageCompleted()
        refreshBlock()
    } else {
        adapter.addData(data.data)
    }

    //设置尾页状态 (包括notifyDateSetChange)
    if (data.page >= data.page_count) {
        adapter.loadMoreEnd()
    } else {
        adapter.loadMoreCompleted()
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