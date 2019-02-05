package uk.nightlines.navigationdrawerindriver.di.main

import ru.terrakok.cicerone.Router
import uk.nightlines.navigationdrawerindriver.UserMode
import uk.nightlines.navigationdrawerindriver.di.navigation.api.NavigationDrawerFragment

interface MainDependencies {
    fun router(): Router

    fun navigationDrawerFragment(): NavigationDrawerFragment

    fun userMode(): UserMode

}