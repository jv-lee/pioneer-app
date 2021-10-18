package com.lee.pioneer.recommend.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.lee.library.cache.CacheManager
import com.lee.library.extensions.getCache
import com.lee.library.extensions.putCache
import com.lee.library.mvvm.viewmodel.CoroutineViewModel
import com.lee.library.mvvm.ui.UiState
import com.lee.library.mvvm.ui.flowCacheLive
import com.lee.library.mvvm.ui.stateCacheLive
import com.lee.pioneer.library.common.constant.CacheConstants.Companion.RECOMMEND_BANNER_KEY
import com.lee.pioneer.library.common.constant.CacheConstants.Companion.RECOMMEND_CACHE_KEY
import com.lee.pioneer.library.common.entity.*
import com.lee.pioneer.library.service.MeService
import com.lee.pioneer.library.service.hepler.ModuleService
import com.lee.pioneer.recommend.model.repository.ApiRepository
import com.lee.pioneer.recommend.view.fragment.RecommendFragment.Companion.TYPE_VIEWS
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * @author jv.lee
 * @date 2020/4/9
 * @description
 */
class RecommendViewModel : CoroutineViewModel() {

    private val meService by lazy { ModuleService.find<MeService>() }

    private val repository by lazy { ApiRepository() }

    lateinit var bannerFlow: StateFlow<UiState>

    private val _typeLive = MutableLiveData(TYPE_VIEWS)
    val typeLive: LiveData<String> = _typeLive

    //获取页面数据列表 根据选中type变更数据
    val contentLive: LiveData<UiState> = typeLive.switchMap { type ->
        stateCacheLive({
            CacheManager.getDefault().getCache<PageData<Content>>(RECOMMEND_CACHE_KEY + type)
        }, {
            repository.api.getHotDataAsync(type)
        }, {
            CacheManager.getDefault().putCache(RECOMMEND_CACHE_KEY + type, it)
        })
    }

    init {
        viewModelScope.launch {
            //获取banner数据 开启缓存模式
            bannerFlow = flowCacheLive({
                CacheManager.getDefault().getCache<ArrayList<Banner>>(RECOMMEND_BANNER_KEY)
            }, {
                repository.api.getBannerAsync().data
            }, {
                CacheManager.getDefault().putCache(RECOMMEND_BANNER_KEY, it.toList())
            }).stateIn(viewModelScope, SharingStarted.WhileSubscribed(),UiState.Default)
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

}