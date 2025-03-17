package br.com.pokedex.core.di

import org.koin.core.qualifier.Qualifier
import org.koin.core.qualifier.QualifierValue

internal object DispatcherIO : Qualifier {

    override val value: QualifierValue = "DispatcherIO"

}

internal object DispatcherMain : Qualifier {

    override val value: QualifierValue = "DispatcherMain"

}

internal object DispatcherDefault : Qualifier {

    override val value: QualifierValue = "DispatcherDefault"

}