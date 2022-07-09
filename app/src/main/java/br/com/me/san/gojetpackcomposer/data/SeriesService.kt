package br.com.me.san.gojetpackcomposer.data

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface SeriesService {

    @GET("?t=Game of Thrones&Season=1&apikey=")
    suspend fun getGotSeasonOne(): Series

    @GET("/")
    suspend fun getPoster(
        @Query("i") imdbId: String,
        @Query("apikey") apikey: String = ""
    ): Poster
}

val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl("https://www.omdbapi.com/")
    .addConverterFactory(MoshiConverterFactory.create())
    .build()

val seriesService: SeriesService = retrofit.create(SeriesService::class.java)