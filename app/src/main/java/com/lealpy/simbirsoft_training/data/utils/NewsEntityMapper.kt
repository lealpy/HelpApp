package com.lealpy.simbirsoft_training.data.utils

import com.lealpy.simbirsoft_training.data.database.news.NewsDescriptionDb
import com.lealpy.simbirsoft_training.data.database.news.NewsEntity
import com.lealpy.simbirsoft_training.data.database.news.NewsPreviewDb
import com.lealpy.simbirsoft_training.domain.model.NewsDescriptionItem
import com.lealpy.simbirsoft_training.domain.model.NewsItem
import com.lealpy.simbirsoft_training.domain.model.NewsPreviewItem

fun List<NewsEntity>.toNewsItems() : List<NewsItem> {
    return this.map { newsEntity ->
        NewsItem(
            id = newsEntity.id,
            imageUrl = newsEntity.imageUrl,
            title = newsEntity.title,
            abbreviatedText = newsEntity.abbreviatedText,
            date = newsEntity.date,
            fundName = newsEntity.fundName,
            address = newsEntity.address,
            phone = newsEntity.phone,
            image2Url = newsEntity.image2Url,
            image3Url = newsEntity.image3Url,
            fullText = newsEntity.fullText,
            isChildrenCategory = newsEntity.isChildrenCategory,
            isAdultCategory = newsEntity.isAdultCategory,
            isElderlyCategory = newsEntity.isElderlyCategory,
            isAnimalCategory = newsEntity.isAnimalCategory,
            isEventCategory = newsEntity.isEventCategory
        )
    }
}

fun List<NewsItem>.toNewsEntities() : List<NewsEntity> {
    return this.map { newsItem ->
        NewsEntity(
            id = newsItem.id,
            imageUrl = newsItem.imageUrl,
            title = newsItem.title,
            abbreviatedText = newsItem.abbreviatedText,
            date = newsItem.date,
            fundName = newsItem.fundName,
            address = newsItem.address,
            phone = newsItem.phone,
            image2Url = newsItem.image2Url,
            image3Url = newsItem.image3Url,
            fullText = newsItem.fullText,
            isChildrenCategory = newsItem.isChildrenCategory,
            isAdultCategory = newsItem.isAdultCategory,
            isElderlyCategory = newsItem.isElderlyCategory,
            isAnimalCategory = newsItem.isAnimalCategory,
            isEventCategory = newsItem.isEventCategory
        )
    }
}

fun List<NewsPreviewDb>.toNewsPreviewItems() : List<NewsPreviewItem> {
    return this.map { newsPreviewEntity ->
        NewsPreviewItem(
            id = newsPreviewEntity.id,
            imageUrl = newsPreviewEntity.imageUrl,
            title = newsPreviewEntity.title,
            abbreviatedText = newsPreviewEntity.abbreviatedText,
            date = newsPreviewEntity.date,
            isChildrenCategory = newsPreviewEntity.isChildrenCategory,
            isAdultCategory = newsPreviewEntity.isAdultCategory,
            isElderlyCategory = newsPreviewEntity.isElderlyCategory,
            isAnimalCategory = newsPreviewEntity.isAnimalCategory,
            isEventCategory = newsPreviewEntity.isEventCategory
        )
    }
}

fun NewsDescriptionDb.toNewsDescriptionItem() : NewsDescriptionItem {
    return NewsDescriptionItem(
        id = this.id,
        imageUrl = this.imageUrl,
        title = this.title,
        abbreviatedText = this.abbreviatedText,
        date = this.date,
        fundName = this.fundName,
        address = this.address,
        phone = this.phone,
        image2Url = this.image2Url,
        image3Url = this.image3Url,
        fullText = this.fullText,
    )
}