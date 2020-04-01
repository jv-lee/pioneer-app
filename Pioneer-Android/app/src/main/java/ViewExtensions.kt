import android.widget.Toast
import androidx.fragment.app.Fragment

/**
 * @author jv.lee
 * @date 2020/4/1
 * @description
 */
fun Fragment.toast2(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(activity, message, duration).show()
}