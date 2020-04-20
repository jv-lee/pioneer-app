import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

/**
 * @author jv.lee
 * @date 2020/4/1
 * @description
 */
fun Fragment.toast2(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(activity, message, duration).show()
}

fun RecyclerView.glideEnable() {
    if (true) return
    addOnScrollListener(object : RecyclerView.OnScrollListener() {
        var isDown = false
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (!isDown) return
            when (newState) {
                //正在拖动
                RecyclerView.SCROLL_STATE_DRAGGING ->
                    context?.let { Glide.with(it).resumeRequests() }
                //滑动停止
                RecyclerView.SCROLL_STATE_IDLE ->
                    context?.let { Glide.with(it).resumeRequests() }
                //惯性滑动中
                RecyclerView.SCROLL_STATE_SETTLING ->
                    context?.let { Glide.with(it).pauseRequests() }
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            isDown = dy >= 0
        }
    })
}