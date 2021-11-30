package com.example.foody.data.database.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.foody.data.Constants.Companion.DEFAULT_ID
import com.example.foody.data.Constants.Companion.FOOD_JOKE_TABLE
import com.example.foody.data.api.models.FoodJokeDataItem

@Entity(tableName = FOOD_JOKE_TABLE)
class FoodJokeEntity(
    @Embedded
    var foodJokeDataItem: FoodJokeDataItem
) {
    @PrimaryKey(autoGenerate = false)
    var id: Int = DEFAULT_ID
}