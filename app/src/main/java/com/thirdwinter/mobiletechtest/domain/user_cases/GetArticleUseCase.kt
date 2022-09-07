package com.thirdwinter.mobiletechtest.domain.user_cases

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
            emit(Resource.Success(getNewsArticleRepo.getNewsArticle()))
        } catch (e: Exception) {
            emit(Resource.Error(e.message))
        }

    }

}

