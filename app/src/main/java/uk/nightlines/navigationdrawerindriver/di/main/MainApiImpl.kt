package uk.nightlines.navigationdrawerindriver.di.main

import android.support.v4.app.Fragment
import uk.nightlines.navigationdrawerindriver.ui.main.MainFragment

class MainApiImpl : MainApi {
    override fun main(): Fragment = MainFragment()
}