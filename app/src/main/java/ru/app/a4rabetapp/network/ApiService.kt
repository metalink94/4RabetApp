package ru.app.a4rabetapp.network

import io.reactivex.Observable
import retrofit2.http.GET
import ru.app.a4rabetapp.models.ResultModel

interface ApiService {

    @GET("/androidapp/api.php")
    fun getResults(): Observable<List<ResultModel>>
}