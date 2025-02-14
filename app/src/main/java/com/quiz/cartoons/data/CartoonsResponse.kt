package com.quiz.cartoons.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CartoonsResponse(
    @SerializedName("results")
    @Expose
    var listPerson: List<Person>? = null
)
data class Person(
    @SerializedName("id")
    @Expose
    var id: Int,
    @SerializedName("name")
    @Expose
    var name: String,
    @SerializedName("status")
    @Expose
    var status: String,
    @SerializedName("species")
    @Expose
    var species: String,
    @SerializedName("type")
    @Expose
    var type: String,
    @SerializedName("gender")
    @Expose
    var gender: String,
    @SerializedName("origin")
    @Expose
    var origin: Origin,
    @SerializedName("location")
    @Expose
    var location: Location,
    @SerializedName("image")
    @Expose
    var image: String,
    @SerializedName("episode")
    @Expose
    var episode: List<String>,
    @SerializedName("url")
    @Expose
    var url: String,
    @SerializedName("created")
    @Expose
    var created: String,
)

data class Origin(
    @SerializedName("name")
    @Expose
    var name: String,
    @SerializedName("url")
    @Expose
    var url: String,
)

data class Location(
    @SerializedName("name")
    @Expose
    var name: String,
    @SerializedName("url")
    @Expose
    var url: String,
)