package com.thirdwinter.mobiletechtest.data.repository

import com.thirdwinter.mobiletechtest.data.model.Article

interface GetNewsArticleRepo {

    suspend fun getNewsArticle():List<Article>

}