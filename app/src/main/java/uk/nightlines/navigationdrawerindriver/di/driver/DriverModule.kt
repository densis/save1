package uk.nightlines.navigationdrawerindriver.di.driver

import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import uk.nightlines.navigationdrawerindriver.UserMode
import uk.nightlines.navigationdrawerindriver.di.driver.api.DriverFlowFragment
import uk.nightlines.navigationdrawerindriver.di.general.PerFeature
import uk.nightlines.navigationdrawerindriver.flow.FlowRouter
import uk.nightlines.navigationdrawerindriver.presenter.driver.DriverFlowPresenter
import uk.nightlines.navigationdrawerindriver.ui.GlobalMenuController
import uk.nightlines.navigationdrawerindriver.ui.driver.DriverFlowFragmentImpl

@Module
class DriverModule {
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
    fun provideGlobalMenuController(): GlobalMenuController =
        GlobalMenuController()

    @PerFeature
    @Provides
    fun provideDriverFlowFragment(): DriverFlowFragment = DriverFlowFragmentImpl()

    @PerFeature
    @Provides
    fun provideDriverFlowPresenter(router: Router, flowRouter: FlowRouter, userMode: UserMode): DriverFlowPresenter = DriverFlowPresenter(router, flowRouter, userMode)

}