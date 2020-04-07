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
    refreshBlock: () -> Unit = {},
    emptyBlock: () -> Unit = {}
) {
    //空数据
    if (data.total_counts == 0) {
        emptyBlock()
        return
    }
    if (data.page == 1) {
        adapter.updateData(data.data)
        adapter.notifyDataSetChanged()
        refreshBlock()
//        if (data.data.isNullOrEmpty() && adapter.data.isNotEmpty()) {
//            emptyBlock()
//        } else {
//
//        }
    } else {
        adapter.addData(data.data)
        if ((data.page * data.page_count) > data.total_counts) {
            adapter.loadMoreEnd()
        } else {
            adapter.loadMoreCompleted()
        }
    }
}