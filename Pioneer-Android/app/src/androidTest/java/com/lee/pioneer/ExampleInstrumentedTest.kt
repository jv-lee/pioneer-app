package com.lee.pioneer

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.lee.library.utils.LogUtil
import com.lee.pioneer.http.ApiImpl
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun testRequest() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.lee.pioneer", appContext.packageName)


        GlobalScope.launch {
            val banners = ApiImpl.getInstance().getBannerAsync().await()
            LogUtil.i("banners->${banners.data.size}")

            val categories = ApiImpl.getInstance().getCategoriesAsync("Article").await()
            LogUtil.i("categories->${categories.data}")

            val dataList =
                ApiImpl.getInstance().getCategoryDataAsync("Article", "Android", 1, 10).await()
            LogUtil.i("dataList->${dataList.data.size}")

            val hotData = ApiImpl.getInstance().getHotDataAsync("views", "Article", 10).await()
            LogUtil.i("hotData->${hotData.data.size}")

            val searchData =
                ApiImpl.getInstance().getSearchDataAsync("设计模式", "Article", "Android", 1, 10)
                    .await()
            LogUtil.i("searchData->${searchData.data.size}")
        }
    }
}
