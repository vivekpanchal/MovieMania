package com.vivek.moviemania.di

import com.vivek.moviemania.data.repository.PokemonRepositoryIml
import com.vivek.moviemania.domain.repository.PokemonRepository
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