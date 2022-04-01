package yunho.app.lolapitest.lolAPI

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import yunho.app.lolapitest.model.Summoner

//롤 API 사용을 위한 인터페이스
//함수 더욱 추가 예정
interface lolAPIService {
    //소환사 이름을 기준으로 검색하는 함수
    @GET("/lol/summoner/v4/summoners/by-name/{summonerName}")
    fun getSummonerInfoByName(
        @Path("summonerName") SummonerName: String,
        @Query("api_key") APIKey : String
    ): Call<Summoner>
}