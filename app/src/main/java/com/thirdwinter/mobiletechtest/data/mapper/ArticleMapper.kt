package com.thirdwinter.mobiletechtest.data.mapper
import com.thirdwinter.mobiletechtest.data.model.Article
import com.thirdwinter.mobiletechtest.data.model.ArticleDTO


fun List<ArticleDTO>.toArticle():List<Article>{
    return map{
        Article(
            content = it.content?:"",
            description = it.description?:"",
            title = it.title?:"",
            urlToImage = it.urlToImage?:"",
            author = it.author?: ""
        )
    }
}