package com.thirdwinter.mobiletechtest.data.mapper

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.thirdwinter.mobiletechtest.data.model.Article
import com.thirdwinter.mobiletechtest.data.model.ArticleDTO


fun List<ArticleDTO>.toArticle(): List<Article> {
    return map {
        Article(
            content = it.content ?: "",
            description = it.description ?: "",
            title = it.title ?: "",
            urlToImage = it.urlToImage ?: "",
            author = it.author ?: ""
        )
    }
}

@TypeConverter
fun List<Article>.toArticleString() = Gson().toJson(this)?: ""

@TypeConverter
fun Article.toArticle(value: String): Article = Gson().fromJson(value, Article::class.java)

@TypeConverter
fun toArticleList(value: String): List<Article> =
    Gson().fromJson(value, object : TypeToken<List<Article>>() {}.type)



