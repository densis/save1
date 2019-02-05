package uk.nightlines.navigationdrawerindriver.di

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import uk.nightlines.navigationdrawerindriver.presenter.main.MainPresenter
import uk.nightlines.navigationdrawerindriver.presenter.nav.NavigationDrawerPresenter
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {
    private val cicerone = Cicerone.create()

    @Provides
    @Singleton
    fun context(): Context = context

    @Provides
    @Singleton
    fun router(): Router = cicerone.router

    @Provides
    @Singleton
    fun navigatorHolder(): NavigatorHolder = cicerone.navigatorHolder
}