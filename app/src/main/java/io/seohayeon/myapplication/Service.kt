package io.seohayeon.myapplication

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

data class MovieResult (
    @SerializedName("results")
    var dailyBoxOfficeList: List<MovieDto> = arrayListOf()
)
data class MovieDto(
    @SerializedName("id")
    var id: Int?,
    @SerializedName("original_title")
    var original_title: String?,
    @SerializedName("overview")
    var overview: String?,
    @SerializedName("poster_path")
    var poster_path: String?,
    @SerializedName("backdrop_path")
    var backdrop_path: String?,
    @SerializedName("release_date")
    var release_date: String?,
    @SerializedName("vote_average")
    var vote_average: String?,
    @SerializedName("vote_count")
    var vote_count: String?
)
interface Service {

    @GET("/3/movie/popular")
    fun getProfile(
        @Query("api_key") api_key:String?,
        @Query("language") language:String?,
    ):Call<MovieResult>
}