package uk.nightlines.navigationdrawerindriver.ui.nav

import uk.nightlines.navigationdrawerindriver.ui.global.MvpView

interface NavigationDrawerView : MvpView {
    fun updateTextSwitchMode(text: String)
}