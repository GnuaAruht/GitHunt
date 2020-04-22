package com.thuraaung.githunt.utils

import com.thuraaung.githunt.model.ModelLanguage
import com.thuraaung.githunt.model.ModelRepo
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

typealias ResponseRepos = Response<List<ModelRepo>>

typealias FlowTrendingRepos = Flow<List<ModelRepo>>

typealias ResponseLanguages = Response<List<ModelLanguage>>

typealias FlowLanguages = Flow<List<ModelLanguage>>

typealias ViewTrendingRepos = ViewState<List<ModelRepo>>

typealias ViewLanguages = ViewState<List<ModelLanguage>>