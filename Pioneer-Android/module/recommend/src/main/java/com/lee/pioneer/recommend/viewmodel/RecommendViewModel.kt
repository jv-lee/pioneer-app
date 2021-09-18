package com.lee.pioneer.recommend.viewmodel

import androidx.lifecycle.*
import com.lee.library.cache.CacheManager
import com.lee.library.extensions.getCache
import com.lee.library.extensions.putCache
import com.lee.library.mvvm.base.BaseViewModel
import com.lee.library.mvvm.ui.UiState
import com.lee.library.mvvm.ui.stateCacheLive
import com.lee.library.mvvm.ui.stateLive
import com.lee.pioneer.library.common.constant.CacheConstants.Companion.RECOMMEND_BANNER_KEY
import com.lee.pioneer.library.common.constant.CacheConstants.Companion.RECOMMEND_CACHE_KEY
import com.lee.pioneer.library.common.constant.KeyConstants.Companion.CATEGORY_RECOMMEND
import com.lee.pioneer.library.common.constant.KeyConstants.Companion.PAGE_COUNT
import com.lee.pioneer.library.common.entity.*
import com.lee.pioneer.library.service.MeService
import com.lee.pioneer.library.service.hepler.ModuleService
import com.lee.pioneer.recommend.model.repository.ApiRepository
import com.lee.pioneer.recommend.view.fragment.RecommendFragment.Companion.TYPE_VIEWS
import kotlinx.coroutines.launch

/**
 * @author jv.lee
 * @date 2020/4/9
 * @description
 */
class RecommendViewModel : BaseViewModel() {

    private val meService by lazy { ModuleService.find<MeService>() }

    private val repository by lazy { ApiRepository() }

    lateinit var bannerLive: LiveData<UiState>

    private val viewsData = arrayListOf<Content>()
    private val likesData = arrayListOf<Content>()
    private val commentsData = arrayListOf<Content>()

    private val _typeLive = MutableLiveData(TYPE_VIEWS)
    val typeLive: LiveData<String> = _typeLive

    val contentLive: LiveData<UiState> = typeLive.switchMap { type ->
        val data = getCacheContentList(type)
        if (data.isNotEmpty()) {
            return@switchMap stateLive { PageData(data, page_count = 0, page = 0) }
        }
        stateCacheLive(
            {
                CacheManager.getDefault().getCache<PageData<Content>>(RECOMMEND_CACHE_KEY + type)
                    ?.also { putCacheContentList(type, ArrayList(it.data)) }
            },
            {
                repository.api.getHotDataAsync(type, CATEGORY_RECOMMEND, PAGE_COUNT)
                    .also { putCacheContentList(type, it.data) }
            },
            {
                CacheManager.getDefault().putCache(RECOMMEND_CACHE_KEY + type, it)
            }
        )
    }

    init {
        viewModelScope.launch {
            //获取banner数据 开启缓存模式
            bannerLive = stateCacheLive(
                {
                    CacheManager.getDefault().getCache<ArrayList<Banner>>(RECOMMEND_BANNER_KEY)
                },
                {
                    repository.api.getBannerAsync().data
                },
                {
                    CacheManager.getDefault().putCache(RECOMMEND_BANNER_KEY, it.toList())
                }
            )
        }
    }

    /**
     * 替换选中类型tab
     */
    fun switchType(type: String) {
        _typeLive.value = type
    }

    /**
     * 浏览记录数据库保存
     */
    fun insertContentHistoryToDB(content: Content) {
        launchMain {
            launchIO {
                val extends = meService.isCollect(content._id)
                meService.insert(
                    ContentHistory.parse(
                        ContentType.CONTENT,
                        ContentSource.ID,
                        extends,
                        content
                    )
                )
            }
        }
    }

    private fun putCacheContentList(type: String, data: ArrayList<Content>) {
        when (type) {
            "views" -> viewsData.addAll(data)
            "likes" -> likesData.addAll(data)
            "comments" -> commentsData.addAll(data)
        }
    }

    private fun getCacheContentList(type: String): ArrayList<Content> {
        return when (type) {
            "views" -> viewsData
            "likes" -> likesData
            "comments" -> commentsData
            else -> viewsData
        }
    }

}