package no.kristiania.covid19app

import com.google.gson.annotations.SerializedName

class Summary(
    @SerializedName("Global") val global : Global,
    @SerializedName("Countries") val countries : List<Countries>,
    @SerializedName("Date") val date : String
)

data class Global (

    @SerializedName("NewConfirmed") val newConfirmed : Int,
    @SerializedName("TotalConfirmed") val totalConfirmed : Int,
    @SerializedName("NewDeaths") val newDeaths : Int,
    @SerializedName("TotalDeaths") val totalDeaths : Int,
    @SerializedName("NewRecovered") val newRecovered : Int,
    @SerializedName("TotalRecovered") val totalRecovered : Int
)

data class Countries (

    @SerializedName("Country") val country : String,
    @SerializedName("CountryCode") val countryCode : String,
    @SerializedName("Slug") val slug : String,
    @SerializedName("NewConfirmed") val newConfirmed : Int,
    @SerializedName("TotalConfirmed") val totalConfirmed : Int,
    @SerializedName("NewDeaths") val newDeaths : Int,
    @SerializedName("TotalDeaths") val totalDeaths : Int,
    @SerializedName("NewRecovered") val newRecovered : Int,
    @SerializedName("TotalRecovered") val totalRecovered : Int,
    @SerializedName("Date") val date : String
)

data class CountryDetails (

    @SerializedName("Country") val country : String,
    @SerializedName("CountryCode") val countryCode : String,
    @SerializedName("Province") val province : String,
    @SerializedName("City") val city : String,
    @SerializedName("CityCode") val cityCode : String,
    @SerializedName("Lat") val lat : Double,
    @SerializedName("Lon") val lon : Double,
    @SerializedName("Confirmed") val confirmed : Int,
    @SerializedName("Deaths") val deaths : Int,
    @SerializedName("Recovered") val recovered : Int,
    @SerializedName("Active") val active : Int,
    @SerializedName("Date") val date : String
)