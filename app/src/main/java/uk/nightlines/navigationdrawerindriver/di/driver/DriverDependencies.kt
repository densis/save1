package uk.nightlines.navigationdrawerindriver.di.driver

import ru.terrakok.cicerone.Router
import uk.nightlines.navigationdrawerindriver.UserMode
import uk.nightlines.navigationdrawerindriver.di.navigation.api.NavigationDrawerFragment

interface DriverDependencies {
    fun router(): Router

    fun navigationDrawerFragment(): NavigationDrawerFragment

    fun userMode(): UserMode
}