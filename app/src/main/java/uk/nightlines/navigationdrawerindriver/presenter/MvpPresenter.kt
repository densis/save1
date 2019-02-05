package uk.nightlines.navigationdrawerindriver.presenter

import uk.nightlines.navigationdrawerindriver.ui.global.MvpView

interface MvpPresenter<V: MvpView> {
    fun attachView(view: V)
    fun detachView()
    fun onDestroy()
}