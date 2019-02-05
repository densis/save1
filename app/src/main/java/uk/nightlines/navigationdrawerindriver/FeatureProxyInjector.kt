package uk.nightlines.navigationdrawerindriver

import ru.terrakok.cicerone.Router
import uk.nightlines.navigationdrawerindriver.di.AppComponent
import uk.nightlines.navigationdrawerindriver.di.driver.DriverComponent
import uk.nightlines.navigationdrawerindriver.di.driver.DriverDependencies
import uk.nightlines.navigationdrawerindriver.di.driver.api.DriverApi
import uk.nightlines.navigationdrawerindriver.di.main.MainApi
import uk.nightlines.navigationdrawerindriver.di.main.MainComponent
import uk.nightlines.navigationdrawerindriver.di.main.MainDependencies
import uk.nightlines.navigationdrawerindriver.di.navigation.NavigationDrawerComponent
import uk.nightlines.navigationdrawerindriver.di.navigation.api.NavigationDrawerApi
import uk.nightlines.navigationdrawerindriver.di.navigation.api.NavigationDrawerDependencies
import uk.nightlines.navigationdrawerindriver.di.navigation.api.NavigationDrawerFragment

object FeatureProxyInjector {
    fun getClientApi(): MainApi =
        MainComponent.initAndGet(object : MainDependencies {
            private val userMode = UserMode.CLIENT
            override fun router(): Router = AppComponent.get().router()
            override fun navigationDrawerFragment(): NavigationDrawerFragment =
                getNavigationDrawer(userMode).navigationDrawerFragment()

            override fun userMode(): UserMode = userMode
        }).getApi()

    fun getDriverApi(): DriverApi = DriverComponent.initAndGet(object : DriverDependencies {
        private val userMode = UserMode.DRIVER
        override fun router(): Router = AppComponent.get().router()
        override fun navigationDrawerFragment(): NavigationDrawerFragment =
            getNavigationDrawer(userMode).navigationDrawerFragment()

        override fun userMode(): UserMode = userMode
    })

    private fun getNavigationDrawer(userMode: UserMode): NavigationDrawerApi =
        NavigationDrawerComponent.initAndGet(object : NavigationDrawerDependencies {
            override fun router(): Router = AppComponent.get().router()
            override fun userMode(): UserMode = userMode
        })
}