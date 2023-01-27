package com.nadim.atlaspackaging

import com.nadim.atlaspackaging.data.RemoteDataRepoImp
import com.nadim.atlaspackaging.domain.RemoteDataRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindFirebaseRepo(remoteDataRepoImp: RemoteDataRepoImp) : RemoteDataRepo
}