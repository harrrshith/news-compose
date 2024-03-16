package com.harshith.news

import android.app.Application
import com.harshith.news.data.AppContainer
import com.harshith.news.data.AppContainerImpl
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NewsApplication : Application(){
    companion object{
        const val NEWS_APP_URI = "https://developer.android.com/jetnews"
    }
}