package uk.nightlines.navigationdrawerindriver.di.navigation

import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import uk.nightlines.navigationdrawerindriver.UserMode
import uk.nightlines.navigationdrawerindriver.di.general.PerFeature
import uk.nightlines.navigationdrawerindriver.di.navigation.api.NavigationDrawerFragment
import uk.nightlines.navigationdrawerindriver.flow.FlowRouter
import uk.nightlines.navigationdrawerindriver.presenter.nav.NavigationDrawerPresenter
import uk.nightlines.navigationdrawerindriver.ui.nav.NavigationDrawerFragmentImpl

@Module
class NavigationDrawerModule {
    @PerFeature
    @Provides
    fun provideCicerone(router: Router): Cicerone<FlowRouter> {
        return Cicerone.create(FlowRouter(router))
    }

    @PerFeature
    @Provides
    fun provideRouter(cicerone: Cicerone<FlowRouter>): FlowRouter = cicerone.router

    @PerFeature
    @Provides
    fun navigatorHolder(cicerone: Cicerone<FlowRouter>): NavigatorHolder = cicerone.navigatorHolder

    @PerFeature
    @Provides
    fun provideNavigationDrawerFragment(): NavigationDrawerFragment = NavigationDrawerFragmentImpl()


    @PerFeature
    @Provides
    fun navigationDrawerPresenter(
        router: Router,
        flowRouter: FlowRouter,
        userMode: UserMode
    ): NavigationDrawerPresenter = NavigationDrawerPresenter(
        router,
        flowRouter,
        userMode
    )
}