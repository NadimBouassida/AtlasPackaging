package com.nadim.atlaspackaging

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    @Named("auth")
    fun provideFirebaseAuthInstance() =  FirebaseAuth.getInstance()

    @Singleton
    @Provides
    @Named("db")
    fun provideDatabaseInstance() =  FirebaseDatabase.getInstance()

}
