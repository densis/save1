package uk.nightlines.navigationdrawerindriver.ui.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v4.view.GravityCompat
import android.view.View
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_main.*
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.commands.Command
import uk.nightlines.navigationdrawerindriver.R
import uk.nightlines.navigationdrawerindriver.Screens
import uk.nightlines.navigationdrawerindriver.di.main.MainComponent
import uk.nightlines.navigationdrawerindriver.flow.FlowRouter
import uk.nightlines.navigationdrawerindriver.presenter.main.MainPresenter
import uk.nightlines.navigationdrawerindriver.setLaunchScreen
import uk.nightlines.navigationdrawerindriver.ui.GlobalMenuController
import uk.nightlines.navigationdrawerindriver.ui.global.BaseFragment
import uk.nightlines.navigationdrawerindriver.ui.nav.NavigationDrawerFragmentImpl
import javax.inject.Inject

class MainFragment: BaseFragment(), MainView {

    @Inject
    lateinit var presenter: MainPresenter

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var router: FlowRouter

    @Inject
    lateinit var globalMenuController: GlobalMenuController

    @Inject
    lateinit var navigationDrawerFragment: NavigationDrawerFragmentImpl

    private val compositeDisposable =  CompositeDisposable()

    override val layoutRes: Int = R.layout.fragment_main

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
        MainComponent.get().inject(this)
        super.onCreate(savedInstanceState)
        presenter.attachView(this)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (childFragmentManager.fragments.isEmpty()) {
            childFragmentManager
                .beginTransaction()
                .replace(R.id.navDrawerContainer, navigationDrawerFragment as BaseFragment)
                .commitNow()
            navigator.setLaunchScreen(Screens.City)
        }
    }

    private fun openNavDrawer(open: Boolean) {
        if (open) drawerLayout.openDrawer(GravityCompat.START)
        else drawerLayout.closeDrawer(GravityCompat.START)
    }

    override fun onResume() {
        super.onResume()
        compositeDisposable.add(
            globalMenuController.state.forEach {
                openNavDrawer(it)
            }
        )
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        compositeDisposable.clear()
        navigatorHolder.removeNavigator()
        super.onPause()
    }


}