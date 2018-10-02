package ru.app.a4rabetapp.network

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    fun build(): Retrofit {
        return Retrofit.Builder()
                .baseUrl("https://applemuch.ru") //Базовая часть адреса
                .addConverterFactory(GsonConverterFactory.create()) //Конвертер, необходимый для преобразования JSON'а в объекты
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }
}