package uk.nightlines.navigationdrawerindriver.ui.nav

import ru.terrakok.cicerone.android.support.SupportAppScreen

data class NavigationDrawerItem (
    val imageUrl: String,
    val title: String,
    var screen: SupportAppScreen
)