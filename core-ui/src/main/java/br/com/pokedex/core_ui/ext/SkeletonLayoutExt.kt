package br.com.pokedex.core_ui.ext

import br.com.pokedex.core.ext.ONE_SECOND_IN_MILLIS
import com.faltenreich.skeletonlayout.SkeletonLayout

var SkeletonLayout.isShimmerVisible: Boolean
    get() = isSkeleton()
    set(value) {
        shimmerDurationInMillis = Long.ONE_SECOND_IN_MILLIS
        if (value) showSkeleton() else showOriginal()
    }