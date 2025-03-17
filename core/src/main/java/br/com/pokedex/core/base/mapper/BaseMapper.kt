package br.com.pokedex.core.base.mapper

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

abstract class BaseMapper<DTO, DomainModel> {

    abstract fun toDomainModel(dto: DTO): DomainModel
    abstract fun toDTO(domainModel: DomainModel): DTO

    @JvmName("toDomainModelNullable")
    fun toDomainModel(dto: DTO?): DomainModel? = dto?.let(::toDomainModel)

    @JvmName("ToDTONullable")
    fun toDTO(domainModel: DomainModel?): DTO? = domainModel?.let(::toDTO)

    fun toDomainModelList(dtoList: List<DTO>): List<DomainModel> =
        dtoList.map { toDomainModel(it) }

    fun toDomainModelList(dtoList: Flow<List<DTO>>) =
        dtoList.map { toDomainModelList(it) }

    fun toDTOList(domainModelList: List<DomainModel>) =
        domainModelList.map { toDTO(it) }

    fun toDTOList(domainModelList: Flow<List<DomainModel>>) =
        domainModelList.map { toDTOList(it) }

}