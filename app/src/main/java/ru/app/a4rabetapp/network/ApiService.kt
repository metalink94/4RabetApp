package ru.app.a4rabetapp.network

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import ru.app.a4rabetapp.models.PostModel

interface ApiService {

    @GET("/api/get")
    fun getResults(@Query("name") resourceName: String,
                   @Query("num") count: Int): Observable<List<PostModel>>
}