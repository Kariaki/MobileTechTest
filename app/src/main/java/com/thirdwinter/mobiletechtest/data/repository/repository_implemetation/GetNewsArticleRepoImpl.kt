package com.thirdwinter.mobiletechtest.data.repository.repository_implemetation

import com.thirdwinter.mobiletechtest.data.mapper.toArticle
import com.thirdwinter.mobiletechtest.data.model.Article
import com.thirdwinter.mobiletechtest.data.repository.GetNewsArticleRepo
import com.thirdwinter.mobiletechtest.data.service.ApiService
import com.thirdwinter.mobiletechtest.util.ApiRequestHandler
import javax.inject.Inject

class GetNewsArticleRepoImpl @Inject constructor(private val apiService: ApiService) :
    GetNewsArticleRepo, ApiRequestHandler() {

    override suspend fun getNewsArticle(): List<Article> {
        /**
         *  this method will throw an exception if theres is an error from the
         *  request handler class
         */
        val response = requestHandler {
            apiService.getNewsArticles()
        }

        return response.articles.toArticle()
    }
}

