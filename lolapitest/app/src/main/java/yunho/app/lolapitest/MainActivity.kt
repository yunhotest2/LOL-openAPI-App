package yunho.app.lolapitest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import yunho.app.lolapitest.lolAPI.lolAPIService
import yunho.app.lolapitest.model.Summoner
import retrofit2.Callback

class MainActivity : AppCompatActivity() {

    //아이디 검색하는 창
    private val searchBar:EditText by lazy {
        findViewById(R.id.searchBar)
    }
    //검색 버튼
    private val searchButton: Button by lazy {
        findViewById(R.id.searchButton)
    }
    //값을 입력하는 textView
    private val accountid:TextView by lazy {
        findViewById(R.id.accountIdVal)
    }
    private val profileIconId:TextView by lazy {
        findViewById(R.id.profileIconIdVal)
    }
    private val revisionDate:TextView by lazy {
        findViewById(R.id.revisionDateVal)
    }
    private val Name:TextView by lazy {
        findViewById(R.id.nameVal)
    }
    private val id:TextView by lazy {
        findViewById(R.id.idVal)
    }
    private val puuid:TextView by lazy {
        findViewById(R.id.puuid)
    }
    private val summonerLevel:TextView by lazy {
        findViewById(R.id.summonerLevelVal)
    }

    // 롤 API 인터페이스
    private lateinit var lolAPI: lolAPIService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://kr.api.riotgames.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        //레트로 핏 라이브러리 빌드
        lolAPI = retrofit.create(lolAPIService::class.java)
        //롤 API 초기화
        setContentView(R.layout.activity_main)
        //일단은 이윤호의 롤 소환사 닉네임 입력
        initSearchButton()
    }
    private fun initSearchButton(){
        searchButton.setOnClickListener {
            Toast.makeText(this,"${searchBar.text}로 검색합니다",Toast.LENGTH_SHORT).show()
            val searchVal: String = searchBar.text.toString()
            searchSummoner(searchVal)
        }
    }
    //소환사 이름기준 검색
    private fun searchSummoner(name: String){
        lolAPI.getSummonerInfoByName(name, API_KEY)
            .enqueue(object : Callback<Summoner> {
                override fun onResponse(
                    call: Call<Summoner>,
                    response: Response<Summoner>)
                {
                    //onResponse 통신 성공시
                    if(response.isSuccessful.not()){
                        //onResponse 통신 성공시
                        return
                    }
                    response.body()?.let {
                        Log.d("MainActivity", it.toString())
                        accountid.text = it.id
                        profileIconId.text = it.profileIcon.toString()
                        revisionDate.text = it.revisionDate.toString()
                        Name.text = it.summonerName
                        summonerLevel.text = it.level.toString()
                        id.text = it.summonerId
                        puuid.text = it.puuId
                    }
                }

                override fun onFailure(call: Call<Summoner>, t: Throwable) {
                    //onResponse 통신 실패시
                }

            })
    }
    //발급받은 롤 API key
    //24시간마다 교체해줘야함
    //롤 개발자 사이트에가서 아이디 입력하고 API 키 발급받은후에 넣어주길바람
    companion object{
        private const val API_KEY = "RGAPI-aa81a9e1-b3cd-4018-9564-60384d2e5a22"
    }
}

