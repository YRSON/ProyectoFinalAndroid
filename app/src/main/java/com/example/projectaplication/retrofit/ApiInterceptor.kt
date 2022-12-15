package rogger.guia.app.apppatitasidat2022.retrofit

import okhttp3.Interceptor
import okhttp3.Response

class ApiInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val token: String = ""
        val request = chain.request().newBuilder().addHeader("Authorization",
        "Baerer $token").build()
        return chain.proceed(request)
    }
}