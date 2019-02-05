package uk.nightlines.navigationdrawerindriver.presenter.driver

import ru.terrakok.cicerone.Router
import uk.nightlines.navigationdrawerindriver.UserMode
import uk.nightlines.navigationdrawerindriver.flow.FlowRouter
import uk.nightlines.navigationdrawerindriver.presenter.MvpBasePresenter
import uk.nightlines.navigationdrawerindriver.ui.global.MvpView
import javax.inject.Inject

class DriverFlowPresenter @Inject constructor(
    private val router: Router,
    private val flowRouter: FlowRouter,
    private val userMode: UserMode
) : MvpBasePresenter<MvpView>() {

}