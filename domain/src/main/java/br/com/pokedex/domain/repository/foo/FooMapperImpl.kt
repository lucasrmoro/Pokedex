package br.com.pokedex.domain.repository.foo

import br.com.pokedex.core_ui.adapter.model.Foo
import br.com.pokedex.entities.db.FooDbEntity

internal class FooMapperImpl : FooMapper() {

    override fun toDomainModel(dto: FooDbEntity): Foo = with(dto) {
        Foo(id = id, msg = msg)
    }

    override fun toDTO(domainModel: Foo): FooDbEntity = with(domainModel) {
        FooDbEntity(id = id, msg = msg)
    }

}