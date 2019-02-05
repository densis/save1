package uk.nightlines.navigationdrawerindriver.di.driver

import dagger.Component
import uk.nightlines.navigationdrawerindriver.di.driver.api.DriverApi
import uk.nightlines.navigationdrawerindriver.di.general.PerFeature
import uk.nightlines.navigationdrawerindriver.ui.driver.DriverFlowFragmentImpl

@Component(
    dependencies = [DriverDependencies::class],
    modules = [DriverModule::class]
)
@PerFeature
abstract class DriverComponent : DriverApi {
    companion object {
        private var component: DriverComponent? = null

        fun initAndGet(dependencies: DriverDependencies): DriverComponent {
            if (component == null) {
                synchronized(DriverComponent::class) {
                    if (component == null) {
                        component = DaggerDriverComponent.builder().driverDependencies(dependencies).build()
                    }
                }
            }
            return component!!
        }

        fun get(): DriverComponent = component!!
    }

    abstract fun inject(driverFlowFragment: DriverFlowFragmentImpl)
}