package uk.nightlines.navigationdrawerindriver.presenter

import android.support.annotation.CallSuper
import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.disposables.CompositeDisposable
import uk.nightlines.navigationdrawerindriver.ui.global.MvpView
import java.lang.ref.WeakReference
import java.util.concurrent.TimeUnit

abstract class MvpBasePresenter<V : MvpView> : MvpPresenter<V> {
    private var viewRef: WeakReference<V>? = null
    protected val view: V? get() = viewRef?.get()

    private var isFirstAttach = false

    protected val compositeDisposable = CompositeDisposable()
    protected val throttleRelay = PublishRelay.create<String>()

    @CallSuper
    override fun attachView(view: V) {
        this.viewRef = WeakReference(view)

        if (!isFirstAttach) {
            onFirstViewAttach()
            isFirstAttach = true
        }
    }

    @CallSuper
    protected open fun onFirstViewAttach() {
        compositeDisposable.add(
            throttleRelay
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe { throttleCommandHandler(it) }
        )
    }

    protected open fun throttleCommandHandler(command: String) {
    }

    @CallSuper
    override fun detachView() {
        viewRef?.clear()
        viewRef = null
    }

    @CallSuper
    override fun onDestroy() {
        compositeDisposable.dispose()
    }

    open fun onBackPressed() {}
}