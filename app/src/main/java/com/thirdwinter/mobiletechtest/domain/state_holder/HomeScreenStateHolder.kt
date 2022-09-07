package com.thirdwinter.mobiletechtest.domain.state_holder

import com.thirdwinter.mobiletechtest.data.model.Article

data class HomeScreenStateHolder(

    val data:List<Article>?=null,
    val errorMessage:String="",
    val isLoading:Boolean=false,

)
