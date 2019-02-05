package uk.nightlines.navigationdrawerindriver.di.main

import dagger.Component
import uk.nightlines.navigationdrawerindriver.di.general.PerFeature
import uk.nightlines.navigationdrawerindriver.ui.main.MainFragment
import java.lang.ref.WeakReference

@Component(
    dependencies = [MainDependencies::class],
    modules = [MainModule::class]
)
@PerFeature
abstract class MainComponent {
    companion object {
        var component: WeakReference<MainComponent?>? = null

        fun initAndGet(dependencies: MainDependencies): MainComponent {
            if (component == null || component?.get() == null) {
                synchronized(MainComponent::class) {
                    if (component == null || component?.get() == null) {
                        component = WeakReference(DaggerMainComponent.builder().mainDependencies(dependencies).build())
                    }
                }
            }
            return component?.get()!!
        }

        fun get(): MainComponent = component?.get()!!
    }

    abstract fun inject(main: MainFragment)
    fun getApi(): MainApi = MainApiImpl()
}