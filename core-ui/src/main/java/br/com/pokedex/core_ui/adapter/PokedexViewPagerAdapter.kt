package br.com.pokedex.core_ui.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class PokedexViewPagerAdapter(
    fragment: Fragment,
    private val screens: List<Fragment>
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = screens.size

    override fun createFragment(position: Int): Fragment = screens[position]

}