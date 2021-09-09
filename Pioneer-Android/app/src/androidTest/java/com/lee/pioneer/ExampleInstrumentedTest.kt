package com.lee.pioneer

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.lee.library.utils.LogUtil
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
            val banners = ApiRepository.getApi().getBannerAsync()
            LogUtil.i("banners->${banners.data.size}")

            val categories = ApiRepository.getApi().getCategoriesAsync("Article")
            LogUtil.i("categories->${categories.data}")

            val dataList =
                ApiRepository.getApi().getContentDataAsync("Article", "Android", 1, 10)
            LogUtil.i("dataList->${dataList.data.size}")

            val hotData =
                ApiRepository.getApi().getHotDataAsync("views", "Article", 10)
            LogUtil.i("hotData->${hotData.data.size}")

            val searchData =
                ApiRepository.getApi().getSearchDataAsync("设计模式", "Article", "Android", 1, 10)
            LogUtil.i("searchData->${searchData.data.size}")
        }
    }
}
