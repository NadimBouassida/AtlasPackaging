package com.nadim.atlaspackaging


import android.app.Application
import androidx.room.Room
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.nadim.atlaspackaging.archive_feature.data.local.ArchiveDatabase
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

    @Singleton
    @Provides
    fun provideArchiveDatabase(app: Application): ArchiveDatabase {
        return Room.databaseBuilder(
            app,
            ArchiveDatabase::class.java,
            "archive.db"
        ).build()
    }
}
