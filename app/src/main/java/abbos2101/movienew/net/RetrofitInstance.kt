package abbos2101.movienew.net

import abbos2101.App
import abbos2101.movienew.common.baseUrl
import com.chuckerteam.chucker.api.ChuckerInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitInstance {
    companion object {
        private var instance: RetrofitService? = null

        fun instance(): RetrofitService {
            if (instance == null) {
                instance = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(baseUrl)
                    .client(okhttpclient())
                    .build()
                    .create(RetrofitService::class.java)
            }
            return instance!!
        }

        private fun okhttpclient(): OkHttpClient {
            return OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(ChuckerInterceptor(App.context!!))
                .build()
        }
    }
}