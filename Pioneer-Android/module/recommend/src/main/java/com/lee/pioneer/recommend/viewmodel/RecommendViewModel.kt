package com.lee.pioneer.recommend.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lee.library.cache.CacheManager
import com.lee.library.extensions.getCache
import com.lee.library.extensions.putCache
import com.lee.library.mvvm.ui.*
import com.lee.library.mvvm.viewmodel.CoroutineViewModel
import com.lee.pioneer.library.common.constant.CacheConstants.Companion.RECOMMEND_BANNER_KEY
import com.lee.pioneer.library.common.constant.CacheConstants.Companion.RECOMMEND_CACHE_KEY
import com.lee.pioneer.library.common.entity.*
import com.lee.pioneer.library.service.MeService
import com.lee.pioneer.library.service.hepler.ModuleService
import com.lee.pioneer.recommend.model.repository.ApiRepository
import com.lee.pioneer.recommend.view.fragment.RecommendFragment.Companion.TYPE_VIEWS
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

/**
 * @author jv.lee
 * @date 2020/4/9
 * @description
 */
class RecommendViewModel : CoroutineViewModel() {

    private val cacheManager = CacheManager.getDefault()

    private val meService = ModuleService.find<MeService>()

    private val repository = ApiRepository()

    private val _bannerLive = MutableLiveData<UiState>()
    val bannerLive: LiveData<UiState> = _bannerLive

    private val _typeAction = MutableStateActionFlow(TYPE_VIEWS)
    val typeAction: StateActionFlow<String> = _typeAction

    val contentFlow: StateFlow<UiState> = typeAction.flatMapLatest { action ->
        stateCacheFlow({
            repository.api.getHotDataAsync(action.value)
        }, {
            cacheManager.getCache<PageData<Content>>(RECOMMEND_CACHE_KEY + action.value)
        }, {
            cacheManager.putCache(RECOMMEND_CACHE_KEY + action.value, it)
        })
    }
        .flowOn(Dispatchers.IO)
        .stateIn(viewModelScope, SharingStarted.Eagerly, UiState.Default)

    //网络获取banner数据及使用本地缓存
    fun requestBanner() {
        launchMain {
            stateCacheFlow({
                repository.api.getBannerAsync().data
            }, {
                cacheManager.getCache<MutableList<Banner>>(RECOMMEND_BANNER_KEY)
            }, {
                cacheManager.putCache(RECOMMEND_BANNER_KEY, it)
            })
                .flowOn(Dispatchers.IO)
                .collectLatest {
                    _bannerLive.postValue(it)
                }
        }
    }

    //替换选中tab类型切换数据
    fun switchType(type: String) {
        _typeAction.emitAction(type)
    }

    /**
     * 浏览记录数据库保存
     */
    fun insertContentHistoryToDB(content: Content) {
        launchIO {
            val extends = meService.isCollect(content._id)
            val contentHistory =
                ContentHistory.parse(ContentType.CONTENT, ContentSource.ID, extends, content)
            meService.insert(contentHistory)
        }
    }

    init {
        requestBanner()
    }

}