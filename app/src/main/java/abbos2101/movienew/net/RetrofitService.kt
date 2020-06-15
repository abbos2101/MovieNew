package abbos2101.movienew.net

import abbos2101.movienew.net.model.DiscoverModel
import abbos2101.movienew.net.model.MovieVideosModel
import abbos2101.movienew.net.model.SearchMovieModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitService {
    @GET("discover/movie")
    suspend fun getDiscoverMovieList(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int = 1,
        @Query("language") language: String = ""
    ): Response<DiscoverModel>

    @GET("movie/{movie_id}/videos")
    suspend fun getMovieVideos(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Response<MovieVideosModel>

    @GET("search/movie")
    suspend fun searchMovie(
        @Query("api_key") apiKey: String,
        @Query("query") query: String = "",
        @Query("page") page: Int = 1,
        @Query("language") language: String = ""
    ): Response<DiscoverModel>
}