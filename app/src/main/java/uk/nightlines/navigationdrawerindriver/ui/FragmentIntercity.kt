package uk.nightlines.navigationdrawerindriver.ui

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.fragment.*
import uk.nightlines.navigationdrawerindriver.R
import uk.nightlines.navigationdrawerindriver.ui.global.BaseFragment

class FragmentIntercity : BaseFragment() {
    override val layoutRes: Int = R.layout.fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        text.text = "fragment intercity"
    }
}