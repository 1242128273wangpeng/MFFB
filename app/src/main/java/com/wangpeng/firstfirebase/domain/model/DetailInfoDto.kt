package com.wangpeng.firstfirebase.domain.model

import com.google.gson.annotations.SerializedName
import java.util.*

/**
{
"id": "5fd4b60db72e4007a931a9ac",
"originalName": "Soul",
"imdbVotes": 498,
"imdbRating": "8.1",
"rottenVotes": null,
"rottenRating": null,
"doubanId": "24733428",
"imdbId": "tt2948372",
"alias": "灵魂奇遇记(港) / 灵魂急转弯(台) / 灵魂 / 心灵",
"doubanVotes": 0,
"doubanRating": "",
"year": "2020",
"type": "Movie",
"duration": 6000,
"dateReleased": "2020-10-11T08:00:00.000+08:00",
"totalSeasons": null,
"episodes": null,
"data": [
{
"genre": "科幻/动画/冒险/音乐",
"name": "心灵奇旅",
"lang": "Cn",
"language": "英语",
"poster": "https://image.querydata.org/movie/poster/1607775758835-236a1f.jpg",
"description": "主角Gardner是一个生活很容易看穿的中学音乐老师，而他真正的梦想是弹钢琴——不是像他现在做的一样只教孩子们弹钢琴，而是以做音乐、做爵士乐来谋生。当他终于拿到人生第一份音乐人的工作，却不小心掉进了下...",
"country": "美国"
},
{
"genre": "Animation/Adventure/Comedy/Family/Fantasy/Music",
"name": "Soul",
"lang": "En",
"language": "English",
"poster": "https://image.querydata.org/movie/poster/1607775760793-9g6817.jpg",
"description": "A musician who has lost his passion for music is transported out of his body and must find his way back with the help of an infant soul learning about herself.",
"country": "USA"
}
],
"director": [
{
"data": [
{
"name": "彼特·道格特",
"lang": "Cn"
},
{
"name": "Pete Docter",
"lang": "En"
}
]
},
{
"data": [
{
"name": "坎普·鲍尔斯",
"lang": "Cn"
},
{
"name": "Kemp Powers",
"lang": "En"
}
]
}
],
"actor": [
{
"data": [
{
"name": "约翰·拉岑贝格",
"lang": "Cn"
},
{
"name": "John Ratzenberger",
"lang": "En"
}
]
},
{
"data": [
{
"name": "杰米·福克斯",
"lang": "Cn"
},
{
"name": "Jamie Foxx",
"lang": "En"
}
]
},
{
"data": [
{
"name": "蒂娜·菲",
"lang": "Cn"
},
{
"name": "Tina Fey",
"lang": "En"
}
]
},
{
"data": [
{
"name": "菲利西亚·拉斯海德",
"lang": "Cn"
},
{
"name": "Phylicia Rashad",
"lang": "En"
}
]
},
{
"data": [
{
"name": "阿米尔-哈利卜·汤普森",
"lang": "Cn"
},
{
"name": "Ahmir-Khalib Thompson",
"lang": "En"
}
]
},
{
"data": [
{
"name": "戴维德·迪格斯",
"lang": "Cn"
},
{
"name": "Daveed Diggs",
"lang": "En"
}
]
}
],
"writer": [
{
"data": [
{
"name": "彼特·道格特",
"lang": "Cn"
},
{
"name": "Pete Docter",
"lang": "En"
}
]
},
{
"data": [
{
"name": "坎普·鲍尔斯",
"lang": "Cn"
},
{
"name": "Kemp Powers",
"lang": "En"
}
]
},
{
"data": [
{
"name": "麦克·琼斯",
"lang": "Cn"
},
{
"name": "Mike Jones",
"lang": "En"
}
]
}
]
}
 */
data class DetailInfoDto(
    var id: String,
    var originalName: String,
    var imdbVotes: Int,
    var imdbRating: String,
    var rottenVotes: String,
    var rottenRating: String,
    var doubanId: String,
    var imdbId: String,
    var alias: String,
    var doubanVotes: Int,
    var doubanRating: String,
    var year: String,
    var type: String,
    var duration: Int,
    var dateReleased: Date,
    var totalSeasons: String,
    var episodes: String,
    @SerializedName("data")
    var desc: List<Desc>,
    @SerializedName("director")
    var director: List<Director>,
    @SerializedName("actor")
    var actor: List<Actor>,
    @SerializedName("writer")
    var writer: List<Writer>
)

data class Desc(
    var genre: String,
    var name: String,
    var lang: String,
    var language: String,
    var poster: String,
    var description: String,
    var country: String
) {
    override fun toString(): String {
        return "Desc(genre='$genre', name='$name', lang='$lang', language='$language', poster='$poster', description='$description', country='$country')"
    }
}

data class Director(var directorDataList: List<Data>) {
    override fun toString(): String {
        return "Director(directorDataList=$directorDataList)"
    }
}

data class Actor(var actorDataList: List<Data>) {
    override fun toString(): String {
        return "Actor(actorDataList=$actorDataList)"
    }
}

data class Writer(var writerDataList: List<Data>) {
    override fun toString(): String {
        return "Writer(writerDataList=$writerDataList)"
    }
}

data class Data(var name: String, var lang: String) {
    override fun toString(): String {
        return "Data(name='$name', lang='$lang')"
    }
}





