package io.seohayeon.myapplication

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


data class MovieInfoDto(
    var adult: Boolean?,
    var backdrop_path: String?,
    var original_title: String?,
    var overview: String?,
    var popularity: Double?,
    var poster_path: String?,
    var runtime: Int?,
    var release_date: String,
)
interface GetMovieInfo {

    @GET("/3/movie/{id}")
    fun getInfo(
        @Path("id") id:Int?,
        @Query("api_key") api_key:String?,
        @Query("language") language:String?,
    ): Call<MovieInfoDto>
}