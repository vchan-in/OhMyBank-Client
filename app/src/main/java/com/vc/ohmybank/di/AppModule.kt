package com.vc.ohmybank.di

import com.vc.ohmybank.api.RetrofitUserInstance
import com.vc.ohmybank.api.UserListService
import com.vc.ohmybank.api.UserActivitiesService
import com.vc.ohmybank.repository.UserActivitiesRepository
import com.vc.ohmybank.repository.UserActivitiesRepositoryImpl
import com.vc.ohmybank.repository.UsersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(RetrofitUserInstance.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideUserListService(retrofit: Retrofit): UserListService =
        retrofit.create(UserListService::class.java)

    @Provides
    @Singleton
    fun provideUserPostService(retrofit: Retrofit): UserActivitiesService =
        retrofit.create(UserActivitiesService::class.java)

    @Provides
    @Singleton
    fun provideUsersRepository(userListService: UserListService): UsersRepository =
        UsersRepository(userListService)

    @Provides
    @Singleton
    fun provideUserPostRepository(userActivitiesService: UserActivitiesService): UserActivitiesRepository =
        UserActivitiesRepositoryImpl(userActivitiesService)
}