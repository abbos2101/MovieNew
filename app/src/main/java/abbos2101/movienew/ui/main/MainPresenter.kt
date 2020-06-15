package abbos2101.movienew.ui.main

import abbos2101.movienew.common.*
import abbos2101.movienew.net.RetrofitInstance
import abbos2101.movienew.net.model.DiscoveryResult
import abbos2101.movienew.ui.content.ContentActivity
import android.content.Context
import android.content.Intent
import android.view.View
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainPresenter(
    private val ctx: Context
) : CoroutineScope {
    private val mainJob = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + mainJob

    private var page = 1
    private val mainAction by lazy { ctx as MainAction }

    fun init() {
        mainAction.onInit()
    }

    fun getMovieList() {
        launch {
            try {
                val response = RetrofitInstance.instance().getDiscoverMovieList(apiKey, page)
                if (response.isSuccessful) {
                    val list = response.body()?.results
                    withContext(Dispatchers.Main) {
                        mainAction.onSetMovieList(list!!)
                        mainAction.onSetProgressBarVisible(View.GONE)
                    }
                }
            } catch (e: Exception) {
            }
        }
    }

    fun searchMovieList(filmName: String) {
        launch {
            try {
                page = 1
                val response = RetrofitInstance.instance().searchMovie(apiKey, filmName, page)
                if (response.isSuccessful) {
                    val list = response.body()?.results
                    withContext(Dispatchers.Main) {
                        mainAction.onSetMovieList(list!!)
                        mainAction.onSetProgressBarVisible(View.GONE)
                    }
                }
            } catch (e: Exception) {
            }
        }
    }

    fun setPageUp() {
        page++
        mainAction.onSetProgressBarVisible(View.VISIBLE)
        getMovieList()
    }

    fun itemClick(model: DiscoveryResult) {
        val intent = Intent(ctx, ContentActivity::class.java)
        intent.putExtra("$content_img_url", "https://image.tmdb.org/t/p/w500${model.poster_path}")
        intent.putExtra("$content_title", "${model.title}")
        intent.putExtra("$content_release", "${model.release_date}")
        intent.putExtra("$content_overview", "${model.overview}")
        intent.putExtra("$content_language", "${model.original_language}")
        intent.putExtra("$content_rating", (model.vote_average * 10).toInt())
        mainAction.onStartActivity(intent)
    }
}