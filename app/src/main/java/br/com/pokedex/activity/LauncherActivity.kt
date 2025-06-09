package br.com.pokedex.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.pokedex.core.ext.openActivity
import br.com.pokedex.pokemons.list.activity.PokemonsListActivity

class LauncherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        openActivity<PokemonsListActivity>(finishCurrent = true, showActivityTransition = false)
    }

}