package abbos2101.movienew.ui.main

import abbos2101.movienew.net.model.DiscoveryResult
import abbos2101.movienew.net.model.SearchMovieResult
import android.content.Intent

interface MainAction {
    fun onInit()
    fun onSetMovieList(newList: ArrayList<DiscoveryResult>)
    fun onSetProgressBarVisible(visibility: Int)
    fun onStartActivity(intent: Intent)
}