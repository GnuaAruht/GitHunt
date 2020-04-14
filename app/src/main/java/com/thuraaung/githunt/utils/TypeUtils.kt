package com.thuraaung.githunt.utils

import com.thuraaung.githunt.ViewState
import com.thuraaung.githunt.model.ModelTrendingRepo
import retrofit2.Response

typealias ResponseTrendingRepos = Response<List<ModelTrendingRepo>>

typealias ViewTrendingRepos = ViewState<List<ModelTrendingRepo>>