package com.wangpeng.firstfirebase.network.api

import com.wangpeng.firstfirebase.domain.model.DetailInfoDto
import com.wangpeng.lib_net.Constant
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface DouBanApi {

//    @GET("j/search_subjects?type=movie&tag=热门&sort=recommend&page_limit=10&page_start=0")
//    suspend fun getDouBanData(): DouBanResponse

//    @Headers("${Constant.DOMAIN}:${Constant.NATIVE_API}")
//    @GET("j/search_subjects?type=movie&tag=冷门佳片&sort=recommend&page_limit=10&page_start=0")
//    suspend fun getDouBanData(): DouBanResponse

    /**
     * 图片信息
     *  https://movie.querydata.org/api/generateimage?id=30163509&lang=Cn
     * {"image":"https://image.querydata.org/movie/poster/1609900940079-5f970c37b8ece21d9aea19ff.png","success":1}
     */

    /**
     * 影片详情
     * https://movie.querydata.org/api?id=35051512
     * {"id":"5f969f94ee3680299115c964","originalName":"我和我的家乡","imdbVotes":186,"imdbRating":"6.9","rottenVotes":null,"rottenRating":null,"doubanId":"35051512","imdbId":"tt12363162","alias":"My People, My Homeland","doubanVotes":302166,"doubanRating":"7.3","year":"2020","type":"Movie","duration":9180,"dateReleased":"2020-10-01T08:00:00.000+08:00","totalSeasons":null,"episodes":null,"data":[{"genre":"剧情/喜剧","name":"我和我的家乡","lang":"Cn","language":"汉语普通话","poster":"https://image.querydata.org/movie/poster/1603706772361-543972.jpg","description":"电影《我和我的家乡》定档2020年国庆，延续《我和我的祖国》集体创作的方式，由张艺谋担当总监制，宁浩担任总导演，张一白担任总策划，宁浩、徐峥、陈思诚、闫非&amp;彭大魔、邓超&amp;俞白眉分别执导五个故事。","country":"中国大陆"},{"genre":"Drama","name":"My People, My Homeland","lang":"En","language":"Chinese","poster":"https://image.querydata.org/movie/poster/1603706773852-daac2b.jpg","description":"In different parts of rural China, various people explore what makes their communities unique.","country":"China"}],"director":[{"data":[{"name":"宁浩","lang":"Cn"},{"name":"Hao Ning","lang":"En"}]},{"data":[{"name":"徐峥","lang":"Cn"},{"name":"Zheng Xu","lang":"En"}]},{"data":[{"name":"陈思诚","lang":"Cn"},{"name":"Sicheng Chen","lang":"En"}]},{"data":[{"name":"闫非","lang":"Cn"},{"name":"Fei Yan","lang":"En"}]},{"data":[{"name":"彭大魔","lang":"Cn"},{"name":"Damo Peng","lang":"En"}]},{"data":[{"name":"邓超","lang":"Cn"},{"name":"Chao Deng","lang":"En"}]},{"data":[{"name":"俞白眉","lang":"Cn"},{"name":"Baimei Yu","lang":"En"}]}],"actor":[{"data":[{"name":"吴京","lang":"Cn"},{"name":"Jing Wu","lang":"En"}]},{"data":[{"name":"徐峥","lang":"Cn"},{"name":"Zheng Xu","lang":"En"}]},{"data":[{"name":"邓超","lang":"Cn"},{"name":"Chao Deng","lang":"En"}]},{"data":[{"name":"葛优","lang":"Cn"},{"name":"You Ge","lang":"En"}]},{"data":[{"name":"黄渤","lang":"Cn"},{"name":"Bo Huang","lang":"En"}]},{"data":[{"name":"范伟","lang":"Cn"},{"name":"Wei Fan","lang":"En"}]},{"data":[{"name":"沈腾","lang":"Cn"},{"name":"Teng Shen","lang":"En"}]},{"data":[{"name":"张占义","lang":"Cn"},{"name":"Zhanyi Zhang","lang":"En"}]},{"data":[{"name":"王宝强","lang":"Cn"},{"name":"Baoqiang Wang","lang":"En"}]},{"data":[{"name":"闫妮","lang":"Cn"},{"name":"Ni Yan","lang":"En"}]},{"data":[{"name":"马丽","lang":"Cn"},{"name":"Li Ma","lang":"En"}]},{"data":[{"name":"刘敏涛","lang":"Cn"},{"name":"Mintao Liu","lang":"En"}]},{"data":[{"name":"刘昊然","lang":"Cn"},{"name":"Haoran Liu","lang":"En"}]},{"data":[{"name":"卢靖姗","lang":"Cn"},{"name":"Celina Jade","lang":"En"}]},{"data":[{"name":"王子文","lang":"Cn"},{"name":"Ziwen Wang","lang":"En"}]},{"data":[{"name":"魏翔","lang":"Cn"},{"name":"Xiang Wei","lang":"En"}]},{"data":[{"name":"章宇","lang":"Cn"},{"name":"Yu Zhang","lang":"En"}]},{"data":[{"name":"王砚辉","lang":"Cn"},{"name":"Yanhui Wang","lang":"En"}]},{"data":[{"name":"张译","lang":"Cn"},{"name":"Yi Zhang","lang":"En"}]},{"data":[{"name":"王源","lang":"Cn"},{"name":"Roy Wang","lang":"En"}]},{"data":[{"name":"张一鸣","lang":"Cn"},{"name":"Yiming Zhang","lang":"En"}]},{"data":[{"name":"杨新鸣","lang":"Cn"},{"name":"Xinming Yang","lang":"En"}]},{"data":[{"name":"王迅","lang":"Cn"},{"name":"Xun Wang","lang":"En"}]},{"data":[{"name":"于和伟","lang":"Cn"},{"name":"Hewei Yu","lang":"En"}]},{"data":[{"name":"苗阜","lang":"Cn"},{"name":"Fu Miao","lang":"En"}]},{"data":[{"name":"辣目洋子","lang":"Cn"},{"name":"Jackie Li","lang":"En"}]},{"data":[{"name":"张子贤","lang":"Cn"},{"name":"Zixian Zhang","lang":"En"}]},{"data":[{"name":"董子健","lang":"Cn"},{"name":"Zijian Dong","lang":"En"}]},{"data":[{"name":"陶虹","lang":"Cn"},{"name":"Hong Tao","lang":"En"}]},{"data":[{"name":"韩彦博","lang":"Cn"},{"name":"Yanbo Han","lang":"En"}]},{"data":[{"name":"吕行","lang":"Cn"},{"name":"Xing Lü","lang":"En"}]},{"data":[{"name":"佟丽娅","lang":"Cn"},{"name":"Liya Tong","lang":"En"}]},{"data":[{"name":"李晨","lang":"Cn"},{"name":"Chen Li","lang":"En"}]},{"data":[{"name":"孙俪","lang":"Cn"},{"name":"Li Sun","lang":"En"}]},{"data":[{"name":"赵铁人","lang":"Cn"},{"name":"Tieren Zhao","lang":"En"}]},{"data":[{"name":"郝云","lang":"Cn"},{"name":"Yun Hao","lang":"En"}]},{"data":[{"name":"彭昱畅","lang":"Cn"},{"name":"Yuchang Peng","lang":"En"}]},{"data":[{"name":"杨紫","lang":"Cn"},{"name":"Andy Yang","lang":"En"}]},{"data":[{"name":"岳云鹏","lang":"Cn"},{"name":"Yunpeng Yue","lang":"En"}]},{"data":[{"name":"赵海燕","lang":"Cn"},{"name":"Haiyan Zhao","lang":"En"}]},{"data":[{"name":"岳小军","lang":"Cn"},{"name":"Xiaojun Yue","lang":"En"}]},{"data":[{"name":"王俊凯","lang":"Cn"},{"name":"Karry Wang","lang":"En"}]},{"data":[{"name":"贾玲","lang":"Cn"},{"name":"Ling Jia","lang":"En"}]},{"data":[{"name":"潘斌龙","lang":"Cn"},{"name":"Binlong Pan","lang":"En"}]},{"data":[{"name":"高睿菲儿","lang":"Cn"},{"name":"Ruifeier Gao","lang":"En"}]},{"data":[{"name":"陈数","lang":"Cn"},{"name":"Shu Chen","lang":"En"}]},{"data":[{"name":"代乐乐","lang":"Cn"},{"name":"Lele Dai","lang":"En"}]},{"data":[{"name":"黄才伦","lang":"Cn"},{"name":"Cailun Huang","lang":"En"}]},{"data":[{"name":"雷佳音","lang":"Cn"},{"name":"Jiayin Lei","lang":"En"}]},{"data":[{"name":"梁超","lang":"Cn"},{"name":"Chao Liang","lang":"En"}]},{"data":[{"name":"孙贵权","lang":"Cn"},{"name":"Guiquan Sun","lang":"En"}]},{"data":[{"name":"韩昊霖","lang":"Cn"},{"name":"Haolin Han","lang":"En"}]},{"data":[{"name":"岳红","lang":"Cn"},{"name":"Hong Yue","lang":"En"}]},{"data":[{"name":"陶亮","lang":"Cn"},{"name":"Liang Tao","lang":"En"}]},{"data":[{"name":"李易峰","lang":"Cn"},{"name":"Yifeng Li","lang":"En"}]},{"data":[{"name":"许猛","lang":"Cn"},{"name":"Meng Xu","lang":"En"}]},{"data":[{"name":"郭帆","lang":"Cn"},{"name":"Frant Gwo","lang":"En"}]},{"data":[{"name":"卫莱","lang":"Cn"},{"name":"Lai Wei","lang":"En"}]},{"data":[{"name":"栗坤","lang":"Cn"},{"name":"Kun Li","lang":"En"}]},{"data":[{"name":"薇娅","lang":"Cn"},{"name":"Viya","lang":"En"}]},{"data":[{"name":"李佳琦","lang":"Cn"},{"name":"Jiaqi Li","lang":"En"}]},{"data":[{"name":"蔡蝶","lang":"Cn"},{"name":"Die Cai","lang":"En"}]},{"data":[{"name":"刘炫锐","lang":"Cn"},{"name":"Xuanrui Liu","lang":"En"}]},{"data":[{"name":"张建亚","lang":"Cn"},{"name":"Jianya Zhang","lang":"En"}]},{"data":[{"name":"张芝华","lang":"Cn"},{"name":"Zhihua Zhang","lang":"En"}]},{"data":[{"name":"孙若淇","lang":"Cn"},{"name":"Ruoqi Sun","lang":"En"}]}],"writer":[]}
     */

    @Headers("${Constant.DOMAIN}:${Constant.NATIVE_API}")
    @GET("j/search_subjects?type=movie&tag=华语&sort=recommend&page_limit=10&page_start=0")
    suspend fun getDouBanData(): DouBanResponse

    @Headers("${Constant.DOMAIN}:${Constant.QUERY_API}")
    @GET("api/")
    suspend fun getDouBanDetail(@Query("id") id: String?): DetailInfoDto?


    @Headers("${Constant.DOMAIN}:${Constant.NATIVE_API}")
    @GET("j/search_subjects?type=movie&tag=华语&sort=recommend&page_limit=10&page_start=0")
    suspend fun getDouBanDataByFlow(): DouBanResponse

    @Headers("${Constant.DOMAIN}:${Constant.QUERY_API}")
    @GET("api/")
    suspend fun getDouBanDetailByFlow(@Query("id") id: String?): DetailInfoDto?

}