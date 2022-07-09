package br.com.me.san.gojetpackcomposer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.me.san.gojetpackcomposer.data.Episode
import br.com.me.san.gojetpackcomposer.data.seriesService
import br.com.me.san.gojetpackcomposer.ui.theme.GoJetpackComposerTheme
import br.com.me.san.gojetpackcomposer.viewmodel.MainViewModel
import coil.compose.rememberAsyncImagePainter

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return MainViewModel(seriesService) as T
            }

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GoJetpackComposerTheme {
                Episodes(episodesData = viewModel.episodesDate)
            }
        }
        viewModel.getSeries()
    }
}

@Composable
fun Episodes(episodesData: LiveData<List<Episode>>) {
    val episodes by episodesData.observeAsState(emptyList())
    LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
        items(items = episodes) { episode ->
            EpisodeItem(episode = episode)
        }
    }
}

@Composable
fun EpisodeItem(episode: Episode) {
    Row {
        Column(modifier = Modifier.padding(16.dp, 8.dp)) {
            val imageModifier = Modifier
                .clip(shape = RoundedCornerShape(6.dp))
            Image(
                painter = rememberAsyncImagePainter(episode.postUrl),
                contentDescription = null,
                modifier = Modifier.size(128.dp)
            )

        }

        Column(modifier = Modifier.padding(0.dp, 8.dp)) {
            Text(text = episode.title)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GoJetpackComposerTheme {
        //Greeting("Android")
    }
}