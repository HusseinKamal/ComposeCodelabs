package com.hussein.composecodelab.compose.room

import androidx.room.Room
import com.hussein.composecodelab.compose.room.Util.DATABASE_NAME
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    single { get<AppDatabase>().userDao() } // Get the UserDao
    viewModel { MyViewModel(get()) }
}