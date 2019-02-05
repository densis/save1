package uk.nightlines.navigationdrawerindriver.di

import android.support.annotation.NonNull
import dagger.Component
import dagger.internal.Preconditions
import ru.terrakok.cicerone.Router
import uk.nightlines.navigationdrawerindriver.App
import uk.nightlines.navigationdrawerindriver.ui.launch.MainActivity
import uk.nightlines.navigationdrawerindriver.ui.main.MainFragment
import javax.inject.Singleton

@Component(
    modules = [AppModule::class]
)
@Singleton
abstract class AppComponent {
    companion object {
        @Volatile
        var instance: AppComponent? = null

        @NonNull
        fun get(): AppComponent = Preconditions.checkNotNull(
            instance,
            "AppComponent is not initialized yet. Call init first."
        )!!

        fun init(@NonNull appComponent: AppComponent) {
            if (instance != null) {
                throw IllegalArgumentException("AppComponent is already initialized.")
            }
            instance = appComponent
        }
    }

    abstract fun router(): Router

    abstract fun inject(app: App)
    abstract fun inject(activity: MainActivity)

}