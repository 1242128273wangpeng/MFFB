package com.wangpeng.firstfirebase.domain.model

data class DouBanModel(
    var rate: String,
    var cover_x: Int,
    var title: String,
    var url: String,
    var playable: Boolean,
    var cover: String,
    var id: String,
    var cover_y: Int,
    var is_new: Boolean
) {
    var isFavorite: Boolean = false
}

fun DouBanDto.toModel(): DouBanModel {
    return DouBanModel(
        rate = rate,
        cover_x = cover_x,
        title = title,
        url = url,
        playable = playable,
        cover = cover,
        id = id,
        cover_y = cover_y,
        is_new = is_new
    )
}

