import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.lee.library.adapter.LeeViewAdapter
import com.lee.pioneer.model.entity.Data

/**
 * @author jv.lee
 * @date 2020/3/30
 * @description 分页数据公共操作类
 */

fun <T> executePageCompleted(
    data: Data<List<T>>,
    adapter: LeeViewAdapter<T>,
    refreshView:SwipeRefreshLayout,
    refreshBlock: () -> Unit = {},
    emptyBlock: () -> Unit = {}
) {
    if (data.page == 1) {
        if (data.data.isNullOrEmpty()) {
            refreshView.isRefreshing = false
            adapter.pageEmpty()
            emptyBlock()
            return
        }
        adapter.updateData(data.data)
        adapter.notifyDataSetChanged()
        refreshView.isRefreshing = false
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
    data: Data<List<T>>,
    adapter: LeeViewAdapter<T>,
    refreshBlock: () -> Unit = {},
    emptyBlock: () -> Unit = {}
) {
    if (data.page == 1) {
        if (data.data.isNullOrEmpty()) {
            emptyBlock()
            return
        }
        adapter.updateData(data.data)
        adapter.notifyDataSetChanged()
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