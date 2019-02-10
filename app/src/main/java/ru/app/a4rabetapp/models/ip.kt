package ru.app.a4rabetapp.models

import com.google.gson.annotations.SerializedName

class IpModel(
        @SerializedName("ip")
        val ip: String = "",
        @SerializedName("type")
        val type: String = "",
        @SerializedName("continent_code")
        val continentType: String = "",
        @SerializedName("continent_name")
        val continent: String = "",
        @SerializedName("country_code")
        val countryCode: String = "",
        @SerializedName("country_name")
        val country: String = "",
        @SerializedName("region_code")
        val regionCode: String = "",
        @SerializedName("region_name")
        val regionName: String = "",
        @SerializedName("city")
        val city: String = "",
        @SerializedName("zip")
        val zip: String = "",
        @SerializedName("latitude")
        val latitude: Double = 0.0,
        @SerializedName("longitude")
        val longitude: Double = 0.0,
        @SerializedName("location")
        val location: IpLocation? = null,
        @SerializedName("calling_code")
        val callingCode: String = ""
)

class IpLocation(
        @SerializedName("geoname_id")
        val geonameId: Long = 0,
        @SerializedName("capital")
        val capital: String = "",
        @SerializedName("languages")
        val languages: MutableList<IpLang> = mutableListOf()
)

class IpLang(
        @SerializedName("code")
        val code: String = "",
        @SerializedName("name")
        val name: String = ""
)