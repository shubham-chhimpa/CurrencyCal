package io.github.shubhamchhimpa.currencycal.main

import io.github.shubhamchhimpa.currencycal.data.CurrencyApi
import io.github.shubhamchhimpa.currencycal.data.models.CurrencyResponse
import io.github.shubhamchhimpa.currencycal.utils.Resource
import java.lang.Exception
import javax.inject.Inject

class DefaultMainRepository @Inject constructor(
    private val currencyApi: CurrencyApi
) : MainRepository {

    override suspend fun getRates(base: String): Resource<CurrencyResponse> {
        return try {
            val response = currencyApi.getRates(base)
            val result = response.body()
            if (response.isSuccessful && result != null) {
                Resource.Success(result)
            } else {
                Resource.Error(response.message())
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "An Error occurred")
        }
    }
}