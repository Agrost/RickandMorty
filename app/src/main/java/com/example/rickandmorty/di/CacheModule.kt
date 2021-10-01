package com.example.rickandmorty.di

import com.example.rickandmorty.data.cache.HomeCache
import com.example.rickandmorty.data.cache.HomeCacheImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface CacheModule {
    @Binds
    @Singleton
    fun bindsCachePage(memoryCacheImpl: HomeCacheImpl): HomeCache
}