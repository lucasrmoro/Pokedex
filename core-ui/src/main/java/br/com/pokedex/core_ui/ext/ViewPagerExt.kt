package br.com.pokedex.core_ui.ext

import android.widget.ScrollView
import androidx.core.view.children
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

fun ViewPager2.attachTo(
    tabLayout: TabLayout,
    setupBlock: (tab: TabLayout.Tab, position: Int) -> Unit
) {
    TabLayoutMediator(tabLayout, this) { tab, pos ->
        setupBlock(tab, pos)
    }.attach()
}

fun ViewPager2.removeFadingEdges() {
    children.firstOrNull()?.overScrollMode = ScrollView.OVER_SCROLL_NEVER
}