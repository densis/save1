package uk.nightlines.navigationdrawerindriver.ui.driver

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.view.View
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.commands.Command
import uk.nightlines.navigationdrawerindriver.R
import uk.nightlines.navigationdrawerindriver.Screens
import uk.nightlines.navigationdrawerindriver.di.driver.DriverComponent
import uk.nightlines.navigationdrawerindriver.di.driver.api.DriverFlowFragment
import uk.nightlines.navigationdrawerindriver.flow.FlowRouter
import uk.nightlines.navigationdrawerindriver.presenter.driver.DriverFlowPresenter
import uk.nightlines.navigationdrawerindriver.setLaunchScreen
import uk.nightlines.navigationdrawerindriver.ui.global.BaseFragment
import uk.nightlines.navigationdrawerindriver.ui.global.MvpView
import uk.nightlines.navigationdrawerindriver.ui.nav.NavigationDrawerFragmentImpl
import javax.inject.Inject

class DriverFlowFragmentImpl : BaseFragment(), DriverFlowFragment, MvpView {

    @Inject
    lateinit var presenter: DriverFlowPresenter

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    override val layoutRes: Int = R.layout.fragment_main

    @Inject
    lateinit var navigationDrawerFragment: NavigationDrawerFragmentImpl

    @Inject
    lateinit var router: FlowRouter

    private val currentFragment
        get() = childFragmentManager.findFragmentById(R.id.mainContainer) as? BaseFragment

    private val drawerFragment
        get() = childFragmentManager.findFragmentById(R.id.navDrawerContainer) as? NavigationDrawerFragmentImpl

    private val navigator: Navigator by lazy {
        object : SupportAppNavigator(this.activity, childFragmentManager, R.id.mainContainer) {

            override fun applyCommands(commands: Array<out Command>?) {
                super.applyCommands(commands)
//                updateNavDrawer()
            }

            override fun activityBack() {
                router.exit()
            }

            override fun setupFragmentTransaction(
                command: Command?,
                currentFragment: Fragment?,
                nextFragment: Fragment?,
                fragmentTransaction: FragmentTransaction
            ) {
                //fix incorrect order lifecycle callback of MainFlowFragment
                fragmentTransaction.setReorderingAllowed(true)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        DriverComponent.get().inject(this)
        super.onCreate(savedInstanceState)

    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attachView(this)
        if (childFragmentManager.fragments.isEmpty()) {
            childFragmentManager
                .beginTransaction()
                .replace(R.id.navDrawerContainer, navigationDrawerFragment as BaseFragment)
                .commitNow()
            navigator.setLaunchScreen(Screens.City)
        }

    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }
}