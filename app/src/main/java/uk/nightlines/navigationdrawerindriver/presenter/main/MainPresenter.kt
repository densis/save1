package uk.nightlines.navigationdrawerindriver.presenter.main

import ru.terrakok.cicerone.Router
import uk.nightlines.navigationdrawerindriver.UserMode
import uk.nightlines.navigationdrawerindriver.flow.FlowRouter
import uk.nightlines.navigationdrawerindriver.presenter.MvpBasePresenter
import uk.nightlines.navigationdrawerindriver.ui.main.MainView
import javax.inject.Inject

class MainPresenter @Inject constructor(

) : MvpBasePresenter<MainView>() {

    override fun attachView(view: MainView) {
        super.attachView(view)
    }
}