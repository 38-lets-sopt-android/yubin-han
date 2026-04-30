package com.example.letssopt.core.data.repository.impl

import com.example.letssopt.R
import com.example.letssopt.core.data.model.home.MainHomeItem
import com.example.letssopt.core.data.repository.api.HomeRepository

class HomeRepositoryImpl : HomeRepository {
    override fun getHomeItems(): List<MainHomeItem> {
        return listOf(
            MainHomeItem.TopBanner(
                banners = listOf(
                    MainHomeItem.Contents("메니페스트", R.drawable.img_poster_manifest),
                    MainHomeItem.Contents("크라임씬", R.drawable.img_poster_crime_scene),
                    MainHomeItem.Contents("폭싹 속았수다", R.drawable.img_poster_jeju_love),
                )
            ),
            MainHomeItem.AlgorithmSection(
                contents = listOf(
                    MainHomeItem.Contents("이 사랑 통역 되나요?", R.drawable.img_poster_love_translate),
                    MainHomeItem.Contents("기묘한 이야기", R.drawable.img_poster_stranger_things),
                    MainHomeItem.Contents("프로젝트 헤일메리", R.drawable.img_poster_hail_mary),
                    MainHomeItem.Contents("주토피아2", R.drawable.img_poster_jootopia2),
                    MainHomeItem.Contents("모아나2", R.drawable.img_poster_moana2),
                    MainHomeItem.Contents("어벤져스: 둠스데이", R.drawable.img_poster_avengers_doomsday),
                )
            ),
            MainHomeItem.UpcomingSection(
                contents = listOf(
                    MainHomeItem.Contents("이 사랑 통역 되나요?", R.drawable.img_poster_love_translate),
                    MainHomeItem.Contents("기묘한 이야기", R.drawable.img_poster_stranger_things),
                    MainHomeItem.Contents("프로젝트 헤일메리", R.drawable.img_poster_hail_mary),
                    MainHomeItem.Contents("주토피아2", R.drawable.img_poster_jootopia2),
                    MainHomeItem.Contents("모아나2", R.drawable.img_poster_moana2),
                    MainHomeItem.Contents("어벤져스: 둠스데이", R.drawable.img_poster_avengers_doomsday),
                )
            ),
            MainHomeItem.PartySection(
                parties = listOf(
                    MainHomeItem.PartyContent(
                        "왕과 사는 남자",
                        "오늘 21:13에 시작",
                        R.drawable.img_poster_king_man
                    ),
                    MainHomeItem.PartyContent(
                        "파묘",
                        "오늘 22:22에 시작",
                        R.drawable.img_poster_exhuma
                    )
                )
            )
        )
    }
}
