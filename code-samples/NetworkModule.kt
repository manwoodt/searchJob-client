@Module
class NetworkModule() {

    @Provides
    @Singleton
    fun provideOkhttp(): OkHttpClient {
        return OkHttpClient().newBuilder().build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit { // in this example gson provider is also needed
        return Retrofit.Builder().addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl("baseUrl...")
            .client(okHttpClient)
            .build()
    }
}