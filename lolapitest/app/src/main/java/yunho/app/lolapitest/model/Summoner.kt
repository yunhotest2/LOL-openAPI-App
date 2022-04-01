package yunho.app.lolapitest.model

import com.google.gson.annotations.SerializedName

//소환사 정보 데이터 클래스

//계정 ID	끈	암호화된 계정 ID입니다. 최대 길이는 56자입니다.
//프로필 아이콘 ID	정수	소환사와 연결된 소환사 아이콘의 ID입니다.
//개정날짜	긴	소환사가 마지막으로 수정된 날짜는 에포크 밀리초로 지정됩니다. 다음 이벤트는 이 타임스탬프를 업데이트합니다: 소환사 이름 변경, 소환사 레벨 변경 또는 프로필 아이콘 변경.
//이름	끈	소환사 이름.
//ID	끈	암호화된 소환사 ID. 최대 길이는 63자입니다.
//푸이드	끈	암호화된 PUUID. 정확한 길이는 78자입니다.
//소환사 레벨	긴	소환사와 관련된 소환사 레벨입니다.

data class Summoner(
    @SerializedName("accountId") val id : String,
    @SerializedName("profileIconId") val profileIcon : Int,
    @SerializedName("revisionDate") val revisionDate : Long,
    @SerializedName("name") val summonerName : String,
    @SerializedName("id") val summonerId : String,
    @SerializedName("puuid") val puuId : String,
    @SerializedName("summonerLevel") val level : Long
)
