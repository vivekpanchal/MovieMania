package com.vivek.pokidex.data.local.converters

import androidx.room.TypeConverter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.vivek.pokidex.data.remote.models.Ability
import com.vivek.pokidex.data.remote.models.Attack
import com.vivek.pokidex.data.remote.models.Resistance
import com.vivek.pokidex.data.remote.models.Weakness

class Converters {

    private val moshi: Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    // Create type adapters for lists of objects
    private val attackListType = Types.newParameterizedType(List::class.java, Attack::class.java)
    private val weaknessListType = Types.newParameterizedType(List::class.java, Weakness::class.java)
    private val abilityListType = Types.newParameterizedType(List::class.java, Ability::class.java)
    private val resistanceListType = Types.newParameterizedType(List::class.java, Resistance::class.java)

    private val attackAdapter: JsonAdapter<List<Attack>> = moshi.adapter(attackListType)
    private val weaknessAdapter: JsonAdapter<List<Weakness>> = moshi.adapter(weaknessListType)
    private val abilityAdapter: JsonAdapter<List<Ability>> = moshi.adapter(abilityListType)
    private val resistanceAdapter: JsonAdapter<List<Resistance>> = moshi.adapter(resistanceListType)

    @TypeConverter
    fun fromAttackList(attacks: List<Attack>?): String? {
        return attacks?.let { attackAdapter.toJson(it) }
    }

    @TypeConverter
    fun toAttackList(attacksString: String?): List<Attack>? {
        return attacksString?.let { attackAdapter.fromJson(it) }
    }

    @TypeConverter
    fun fromWeaknessList(weaknesses: List<Weakness>?): String? {
        return weaknesses?.let { weaknessAdapter.toJson(it) }
    }

    @TypeConverter
    fun toWeaknessList(weaknessesString: String?): List<Weakness>? {
        return weaknessesString?.let { weaknessAdapter.fromJson(it) }
    }

    @TypeConverter
    fun fromAbilityList(abilities: List<Ability>?): String? {
        return abilities?.let { abilityAdapter.toJson(it) }
    }

    @TypeConverter
    fun toAbilityList(abilitiesString: String?): List<Ability>? {
        return abilitiesString?.let { abilityAdapter.fromJson(it) }
    }

    @TypeConverter
    fun fromResistanceList(resistances: List<Resistance>?): String? {
        return resistances?.let { resistanceAdapter.toJson(it) }
    }

    @TypeConverter
    fun toResistanceList(resistancesString: String?): List<Resistance>? {
        return resistancesString?.let { resistanceAdapter.fromJson(it) }
    }
}
