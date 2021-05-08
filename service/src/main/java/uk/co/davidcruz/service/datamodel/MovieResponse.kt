package uk.co.davidcruz.service.datamodel

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @field:SerializedName("data")
    val data: List<DataItem?>? = null
)

data class DataItem(
    @field:SerializedName("year")
    val year: String? = null,

    @field:SerializedName("genre")
    val genre: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("poster")
    val poster: String? = null
)
