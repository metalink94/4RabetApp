package ru.app.a4rabetapp.network

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IpService {

    @GET("{ip}")
    fun getIpLocation(@Path("ip") userIp: String,
                      @Query("access_key") key: String): Observable<String>
}