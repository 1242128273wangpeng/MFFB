package com.wangpeng.firstfirebase.domain.model

import com.google.gson.annotations.SerializedName
import java.util.*

class DetailInfoModel(
    var id: String ? = null,
    var originalName: String? = null,
    var imdbVotes: Int = 0,
    var imdbRating: String ? = null,
    var doubanId: String? = null,
    var imdbId: String ? = null,
    var alias: String ? = null,
    var doubanVotes: Int = 0,
    var doubanRating: String ? = null,
    var year: String ? = null,
    var type: String? = null,
    var duration: Int = 0,
    var dateReleased: Date? = null,
    @SerializedName("data")
    var desc: List<Desc>? = null,
    @SerializedName("director")
    var director: List<Director>? = null,
    @SerializedName("actor")
    var actor: List<Actor>? = null,
    @SerializedName("writer")
    var writer: List<Writer>? = null
) {
    override fun toString(): String {
        return "DetailInfoModel(id='$id', originalName='$originalName', imdbVotes=$imdbVotes, imdbRating='$imdbRating', doubanId='$doubanId', imdbId='$imdbId', alias='$alias', doubanVotes=$doubanVotes, doubanRating='$doubanRating', year='$year', type='$type', duration=$duration, dateReleased=$dateReleased, desc=$desc, director=$director, actor=$actor, writer=$writer)"
    }
}

fun DetailInfoDto?.toModel(): DetailInfoModel {
    if (this == null) {
        return DetailInfoModel()
    } else {
        return DetailInfoModel(
            id = id,
            originalName = originalName,
            imdbVotes = imdbVotes,
            imdbRating = imdbRating,
            doubanId = doubanId,
            imdbId = imdbId,
            alias = alias,
            doubanVotes = doubanVotes,
            doubanRating = doubanRating,
            year = year,
            type = type,
            duration = duration,
            dateReleased = dateReleased,
            desc = desc,
            director = director,
            actor = actor,
            writer = writer
        )
    }
}