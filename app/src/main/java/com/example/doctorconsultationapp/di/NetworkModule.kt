package com.example.doctorconsultationapp.di

import com.example.doctorconsultationapp.BuildConfig
import com.example.doctorconsultationapp.networking.ApiService
import com.example.doctorconsultationapp.utilities.constants.NetworkConstants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Credentials
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {
    @Provides
    fun providesBaseUrl(): String = NetworkConstants.dummyURL

    @Provides
    fun providesHttpLogger(): HttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    fun providesGson(): Gson = GsonBuilder().create()

    @Provides
    fun providesGsonFactory(gson: Gson): GsonConverterFactory =  GsonConverterFactory.create(gson)

    @Provides
    fun providesNetworkInterceptor(): Request {
        return Request.Builder()
            .header("platform", "android")
            .header("app-version", BuildConfig.VERSION_NAME)
            .build()
    }

    @Provides
    fun providesNetworkingTimeout(): Long = NetworkConstants.requestTimeoutInSeconds


    @Provides
    fun providesOkHttpClient(timeOutInSeconds: Long, interceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(timeOutInSeconds, TimeUnit.SECONDS)
            .writeTimeout(timeOutInSeconds, TimeUnit.SECONDS)
            .connectTimeout(timeOutInSeconds, TimeUnit.SECONDS)
            .addInterceptor{chain ->
                val original = chain.request()
                val request = original.newBuilder()
                    .header("platform", "android")
                    .header("app-version", BuildConfig.VERSION_NAME)
                    .header("Authorization",NetworkConstants.basicAuth)
                    .method(original.method, original.body)
                    .build()
                chain.proceed(request)
            }
            .addInterceptor(interceptor)

            .build()
    }

    @Provides
    fun providesRetrofit(baseUrl: String, gsonConverterFactory: GsonConverterFactory, okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(gsonConverterFactory)
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

}