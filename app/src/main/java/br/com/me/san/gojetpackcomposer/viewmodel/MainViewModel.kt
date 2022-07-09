package br.com.me.san.gojetpackcomposer.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.me.san.gojetpackcomposer.data.Episode
import br.com.me.san.gojetpackcomposer.data.Series
import br.com.me.san.gojetpackcomposer.data.SeriesService
import kotlinx.coroutines.launch

class MainViewModel(private val seriesService: SeriesService) : ViewModel() {

    private val _episodesDate = MutableLiveData<List<Episode>>()
    val episodesDate: LiveData<List<Episode>>
        get() = _episodesDate

    fun getSeries() {
        viewModelScope.launch {
            try {
                /**
                 * Fazer chama para a api de séries
                 * val series = seriesService.getGotSeasonOne()
                 * for (episode in series.episodes) {
                        val poster = seriesService.getPoster(episode.imdbId)
                        episode.postUrl = poster.url
                    }
                 * */
                val series = buildListSerie()

                _episodesDate.value = series.episodes
            } catch (ex: Exception) {
                Log.d("Service error", ex.toString())
            }
        }
    }

    fun buildListSerie(): Series =
        Series(
            title = "GOT",
            episodes = listOf(
                Episode("Episode one", "01"),
                Episode("Episode tow", "02"),
                Episode("Episode thre", "03"),
                Episode("Episode four", "04"),
                Episode("Episode five", "05"),
                Episode("Episode six", "06"),
                Episode("Episode seven", "07"),
                Episode("Episode eight", "08"),
                Episode("Episode nine", "09"),
                Episode("Episode ten", "10"),
            )
        )
}