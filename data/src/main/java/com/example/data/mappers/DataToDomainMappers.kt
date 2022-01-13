package com.example.data.mappers

import com.example.data.api.models.IngredientPreviewDataItem
import com.example.domain.models.IngredientPreviewDomain

fun IngredientPreviewDataItem.convertToDomainItem(): IngredientPreviewDomain {
    return IngredientPreviewDomain(
        id,
        image,
        name
    )
}