import com.lee.library.adapter.LeeViewAdapter
import com.lee.pioneer.model.entity.Data

/**
 * @author jv.lee
 * @date 2020/3/30
 * @description 分页数据公共操作类
 */

fun <T> executePageCompleted(
    data: Data<T>,
    adapter: LeeViewAdapter<T>,
    refreshBlock: () -> Unit = {},
    emptyBlock: () -> Unit = {}
) {
    if (data.page == 1) {
        adapter.updateData(data.data)
        adapter.notifyDataSetChanged()
        if (data.data.isNullOrEmpty() && adapter.data.isNotEmpty()) {
            emptyBlock()
        } else {
            refreshBlock()
        }
    } else {
        adapter.addData(data.data)
        if ((data.page * data.page_count) > data.total_counts) {
            adapter.loadMoreEnd()
        } else {
            adapter.loadMoreCompleted()
        }
    }
}