package uk.nightlines.navigationdrawerindriver

import android.app.Application
import uk.nightlines.navigationdrawerindriver.di.AppComponent
import uk.nightlines.navigationdrawerindriver.di.AppModule
import uk.nightlines.navigationdrawerindriver.di.DaggerAppComponent

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        AppComponent.init(
            DaggerAppComponent.builder().appModule(AppModule( applicationContext)).build()
        )
        AppComponent.get().inject(this)
    }
}