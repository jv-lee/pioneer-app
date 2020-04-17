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

fun RecyclerView.glideEnable(){
    addOnScrollListener(object:RecyclerView.OnScrollListener(){
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if(getContext() != null) Glide.with(getContext()).resumeRequests()
        }
    })
}