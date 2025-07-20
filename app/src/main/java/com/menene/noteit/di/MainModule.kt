package com.menene.noteit.di

import android.app.Application
import com.menene.noteit.util.DataStoreManager
import org.koin.dsl.module

val mainModule = module {
    single {
        DataStoreManager(get<Application>())
    }
}