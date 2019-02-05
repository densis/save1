package uk.nightlines.navigationdrawerindriver.di.navigation.api

import ru.terrakok.cicerone.Router
import uk.nightlines.navigationdrawerindriver.UserMode

interface NavigationDrawerDependencies {
    fun router(): Router
    fun userMode(): UserMode
}