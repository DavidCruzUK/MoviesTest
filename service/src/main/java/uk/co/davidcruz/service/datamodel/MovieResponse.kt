package uk.co.davidcruz.service.datamodel

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @field:SerializedName("data")
    val data: List<DataItem>
)

data class DataItem(
    @field:SerializedName("year")
    val year: String,

    @field:SerializedName("genre")
    val genre: String,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("poster")
    val poster: String,
)
