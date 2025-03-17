package br.com.pokedex.featurea.presentation.activity

import android.os.Bundle
import br.com.pokedex.core_ui.base.activity.BaseContainerActivity
import br.com.pokedex.featurea.di.FeatureAModule
import br.com.pokedex.featurea.presentation.fragment.ListFragment
import br.com.pokedex.featurea.presentation.viewModel.FeatureAViewModel

internal class FeatureAActivity : BaseContainerActivity<FeatureAViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FeatureAModule.init()
        navigate(ListFragment.newInstance())
    }

}