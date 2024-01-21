package com.example.data.api

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Provides
    fun provideLoggingInterceptor():HttpLoggingInterceptor{
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return interceptor
    }

    @Provides
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor):OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    fun provideGsonConvertFactory():GsonConverterFactory{
        return GsonConverterFactory.create()
    }


    @Provides
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ):Retrofit{
        return Retrofit
            .Builder()
            .client(okHttpClient)
            .baseUrl(APIConstants.BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .build()

    }
    @Provides
    fun provideWebServices(
        retrofit: Retrofit
    ):WebServices{
        return retrofit.create(WebServices::class.java)
    }
}