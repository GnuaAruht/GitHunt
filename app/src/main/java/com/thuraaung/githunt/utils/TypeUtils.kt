package com.thuraaung.githunt.utils

import com.thuraaung.githunt.ViewState
import com.thuraaung.githunt.model.ModelLanguage
import com.thuraaung.githunt.model.ModelTrendingRepo
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

typealias ResponseTrendingRepos = Response<List<ModelTrendingRepo>>

typealias FlowTrendingRepos = Flow<List<ModelTrendingRepo>>

typealias ResponseLanguages = Response<List<ModelLanguage>>

typealias FlowLanguages = Flow<List<ModelLanguage>>

typealias ViewTrendingRepos = ViewState<List<ModelTrendingRepo>>