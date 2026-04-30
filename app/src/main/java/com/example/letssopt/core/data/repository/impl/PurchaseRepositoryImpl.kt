package com.example.letssopt.core.data.repository.impl

import com.example.letssopt.R
import com.example.letssopt.core.data.model.purchase.PurchaseContent
import com.example.letssopt.core.data.repository.api.PurchaseRepository


class PurchaseRepositoryImpl : PurchaseRepository {
    override fun getPurchaseItems(): List<PurchaseContent> {
        return listOf(
            PurchaseContent("이 사랑 통역 되나요?", R.drawable.img_poster_love_translate),
            PurchaseContent("기묘한 이야기5", R.drawable.img_poster_stranger_things),
            PurchaseContent("프로젝트 헤일메리", R.drawable.img_poster_hail_mary),
            PurchaseContent("주토피아2", R.drawable.img_poster_jootopia2),
            PurchaseContent("모아나2", R.drawable.img_poster_moana2),
            PurchaseContent("어벤져스: 둠스데이", R.drawable.img_poster_avengers_doomsday),
        )
    }
}
