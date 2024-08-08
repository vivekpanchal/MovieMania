package com.vivek.pokidex.data.remote

import androidx.paging.*
import androidx.room.withTransaction
import com.vivek.pokidex.data.local.AppDatabase
import com.vivek.pokidex.data.local.entity.PokemonEntity
import com.vivek.pokidex.data.remote.models.toEntity
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class PokemonRemoteMediator(
    private val database: AppDatabase,
    private val apiService: ApiService
) : RemoteMediator<Int, PokemonEntity>() {

    private var totalItemCount = 0  // This will store the total count of items from the API

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PokemonEntity>
    ): MediatorResult {
        val page = when (loadType) {
            LoadType.REFRESH -> {
                Timber.d("REFRESH: ${state.lastItemOrNull()}")
                1
            }
            LoadType.PREPEND -> {
                Timber.d("PREPEND: ${state.firstItemOrNull()}")
                return MediatorResult.Success(endOfPaginationReached = true)
            }
            LoadType.APPEND -> {
                Timber.d("APPEND: ${state.lastItemOrNull()}")
                val lastItem = state.lastItemOrNull() ?: return MediatorResult.Success(endOfPaginationReached = true)
                lastItem.page + 1
            }
        }

        // Check if the next page is beyond total available pages
        if (totalItemCount > 0 && page > (totalItemCount + state.config.pageSize - 1) / state.config.pageSize) {
            return MediatorResult.Success(endOfPaginationReached = true)
        }

        try {
            val response = apiService.getPokemonCards(pageSize = state.config.pageSize, page = page)
            val pokemonList = response.data.map { it.toEntity(page) }
            // Capture total count from the first response
            if (totalItemCount == 0) {
                totalItemCount = response.totalCount
                Timber.d("Total items available: $totalItemCount")
            }
            Timber.d("Fetched ${pokemonList.size} items for page $page")

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    Timber.d("Clearing database")
                    database.pokemonDao().clearAll()
                }
                Timber.d("Inserting items into database")
                database.pokemonDao().insertAll(pokemonList)
            }
            val endOfPaginationReached = (page * state.config.pageSize) >= totalItemCount || pokemonList.isEmpty()
            Timber.d("End of pagination reached: $endOfPaginationReached")
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (exception: IOException) {
            return MediatorResult.Error(exception)
        } catch (exception: HttpException) {
            return MediatorResult.Error(exception)
        }
    }
}
