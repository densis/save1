package uk.nightlines.navigationdrawerindriver.di.navigation

import dagger.Component
import uk.nightlines.navigationdrawerindriver.di.general.PerFeature
import uk.nightlines.navigationdrawerindriver.di.navigation.api.NavigationDrawerApi
import uk.nightlines.navigationdrawerindriver.di.navigation.api.NavigationDrawerDependencies
import uk.nightlines.navigationdrawerindriver.ui.nav.NavigationDrawerFragmentImpl
import java.lang.ref.WeakReference

@Component(
    dependencies = [NavigationDrawerDependencies::class],
    modules = [NavigationDrawerModule::class]
)
@PerFeature
abstract class NavigationDrawerComponent : NavigationDrawerApi {
    companion object {
        private var componentWeak: WeakReference<NavigationDrawerComponent?>? = null

        fun initAndGet(dependencies: NavigationDrawerDependencies): NavigationDrawerComponent {
            if (componentWeak == null || componentWeak?.get() == null) {
                synchronized(NavigationDrawerComponent::class) {
                    if (componentWeak == null || componentWeak?.get() == null) {
                        componentWeak = WeakReference(
                            DaggerNavigationDrawerComponent.builder().navigationDrawerDependencies(
                                dependencies
                            ).build()
                        )
                    }
                }
            }
            return componentWeak?.get()!!
        }

        fun get(): NavigationDrawerComponent = componentWeak?.get()!!
    }

    abstract fun inject(navigationDrawerFragment: NavigationDrawerFragmentImpl)
}