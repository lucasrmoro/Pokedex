package br.com.pokedex.domain.repository.foo

import br.com.pokedex.core.base.mapper.BaseMapper
import br.com.pokedex.core_ui.adapter.model.Foo
import br.com.pokedex.entities.db.FooDbEntity

abstract class FooMapper : BaseMapper<FooDbEntity, Foo>()