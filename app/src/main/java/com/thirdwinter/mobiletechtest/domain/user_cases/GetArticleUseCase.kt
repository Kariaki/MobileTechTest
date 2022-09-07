package com.thirdwinter.mobiletechtest.domain.user_cases

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.util.Log
import androidx.core.content.ContextCompat
import com.google.android.gms.common.util.SharedPreferencesUtils
import com.thirdwinter.mobiletechtest.data.mapper.toArticleList
import com.thirdwinter.mobiletechtest.data.mapper.toArticleString
import com.thirdwinter.mobiletechtest.data.model.Article
import com.thirdwinter.mobiletechtest.data.repository.GetNewsArticleRepo
import com.thirdwinter.mobiletechtest.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetArticleUseCase @Inject constructor(private val getNewsArticleRepo: GetNewsArticleRepo) {


    operator fun invoke(): Flow<Resource<List<Article>>> = flow {

        emit(Resource.Loading()) // emit loading state initially to show progress bar on the UI

        try {
           val result = getNewsArticleRepo.getNewsArticle();
            emit(Resource.Success(getNewsArticleRepo.getNewsArticle()))
        } catch (e: Exception) {
            emit(Resource.Error(e.message))
        }

    }

     fun cacheArticle(context: Context,articles:List<Article>){

        val preferences = context.getSharedPreferences("cache",Context.MODE_PRIVATE).edit()
         val result =articles.toArticleString()
         Log.d("MainActivity",result)
         
         preferences.putString("articles",result).apply()


    }
    fun getCachedArticles(context: Context):List<Article>{
        val preferences = context.getSharedPreferences("cache",Context.MODE_PRIVATE)
        val cacheObj = preferences.getString("articles","")!!
        if(cacheObj.isEmpty())
            return  listOf();

        return  toArticleList(cacheObj);

    }

}

