package com.vivek.pokidex.di

import com.vivek.pokidex.data.repository.PokemonRepositoryIml
import com.vivek.pokidex.domain.repository.PokemonRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
abstract class RepoModule {

    @Binds
    abstract fun bindRepository(impl: PokemonRepositoryIml): PokemonRepository
}