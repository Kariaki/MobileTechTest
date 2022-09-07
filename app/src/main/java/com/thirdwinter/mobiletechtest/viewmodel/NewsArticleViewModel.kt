package com.thirdwinter.mobiletechtest.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thirdwinter.mobiletechtest.domain.state_holder.HomeScreenStateHolder
import com.thirdwinter.mobiletechtest.domain.user_cases.GetArticleUseCase
import com.thirdwinter.mobiletechtest.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class NewsArticleViewModel @Inject constructor(private val getArticleUseCase: GetArticleUseCase) :
    ViewModel() {


        val articles = mutableStateOf(HomeScreenStateHolder())

    init {
        getNewsArticles()
    }

    private fun getNewsArticles(){

        getArticleUseCase().onEach {
            when(it){
                is Resource.Loading->{
                    articles.value = HomeScreenStateHolder(isLoading = true)
                }
                is Resource.Success->{
                    articles.value = HomeScreenStateHolder(data = it.data)
                }
                is Resource.Error->{
                    articles.value = HomeScreenStateHolder(errorMessage = it.message.toString())
                }
            }
        }.launchIn(viewModelScope)


    }

}