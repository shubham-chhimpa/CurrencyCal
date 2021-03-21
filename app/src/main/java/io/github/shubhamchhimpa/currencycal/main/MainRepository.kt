package io.github.shubhamchhimpa.currencycal.main

import io.github.shubhamchhimpa.currencycal.data.models.CurrencyResponse
import io.github.shubhamchhimpa.currencycal.utils.Resource

interface MainRepository {
    suspend fun getRates(base:String): Resource<CurrencyResponse>
}