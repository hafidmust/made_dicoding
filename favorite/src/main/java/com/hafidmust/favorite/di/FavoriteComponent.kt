package com.hafidmust.favorite.di

import android.content.Context
import com.hafidmust.favorite.FavoriteActivity
import com.hafidmust.madestories.di.FavoriteModuleDependencies
import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [FavoriteModuleDependencies::class])
interface FavoriteComponent {
    fun injectFavoriteStories(activity : FavoriteActivity)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context : Context) : Builder
        fun appDependencies(favoriteModuleDependencies: FavoriteModuleDependencies) : Builder
        fun build() : FavoriteComponent
    }
}