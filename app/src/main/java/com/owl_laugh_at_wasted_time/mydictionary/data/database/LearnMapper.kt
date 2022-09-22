package com.owl_laugh_at_wasted_time.mydictionary.data.database

import com.owl_laugh_at_wasted_time.mydictionary.domain.entity.WordItem
import javax.inject.Inject


class LearnMapper @Inject constructor() {

    fun mapEntityToDbModel(item: WordItem) = LearnItemDBModel(
        word = item.word,
        translation = item.translation,
        done = item.done
    )

    fun mapDbModelToEntity(itemDBModel: LearnItemDBModel) = WordItem(
        word = itemDBModel.word,
        translation = itemDBModel.translation,
        done = itemDBModel.done
    )

    fun mapListDbModelToListEntity(list: List<LearnItemDBModel>) = list.map {
        mapDbModelToEntity(it)
    }
}