package com.lealpy.help_app.data.repository

import com.lealpy.help_app.domain.model.NewsDescriptionItem
import com.lealpy.help_app.domain.model.NewsPreviewItem
import com.lealpy.help_app.domain.repository.NewsRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class TestNewsRepositoryImpl : NewsRepository {

    override fun getAllNewsPreviewItems(): Single<List<NewsPreviewItem>> {
        return Single.just(TEST_NEWS_PREVIEW_ITEMS)
    }

    override fun getNewsDescriptionItem(id: Long): Single<NewsDescriptionItem> {
        return Single.just(TEST_NEWS_DESCRIPTION_ITEM)
    }

    override fun funInsertToDbWatchedNewsId(id: Long): Completable {
        return Completable.complete()
    }

    override fun getUnwatchedNewsNumber(): Single<Int> {
        return Single.just(5)
    }

    companion object {
        val TEST_NEWS_DESCRIPTION_ITEM = NewsDescriptionItem(
            id = 1L,
            imageUrl = "https://user-images.githubusercontent.com/90380451/148293903-d865011e-b5c7-4c7d-a74b-7bdf6ef8e54f.png",
            title = "Конкурс по вокальному пению в детском доме №6",
            abbreviatedText = "Дубовская школа-интернат для детей с ограниченными возможностями здоровья стала первой в области …",
            date = 1610755200000,
            fundName = "Благотворительный фонд «Обнаженные сердца»",
            address = "Брянская область, Дубровский район, пгт. Дубровка, ул. Журавлева, д. 50",
            phone = "8 999 111 22 33",
            image2Url = "https://user-images.githubusercontent.com/90380451/148293731-e78303ea-1242-474d-94d6-9b44fd5e9bcd.png",
            image3Url = "https://user-images.githubusercontent.com/90380451/148293732-3fabffab-559b-47cf-8862-af0c1c238d67.png",
            fullText = "Воспитанница Дубровского детского дома - интерната Ольгина Ольга заняла 1 место в областном конкурсе по вокальному пению. Мы сердечно поздравляем Олю и желаем ей дальнейших успехов в творческой деятельности!",
        )

        val TEST_NEWS_PREVIEW_ITEMS = listOf(
            NewsPreviewItem(
                id = 0,
                imageUrl = "https://user-images.githubusercontent.com/90380451/148294432-59c570bd-443a-4951-936c-7ed5cb13219e.png",
                title = "Спонсоры отремонтируют школу-интернат",
                abbreviatedText = "Дубовская школа-интернат для детей с ограниченными возможностями здоровья стала первой в области …",
                date = 1612137600000,
                isChildrenCategory = true,
                isAdultCategory = false,
                isElderlyCategory = false,
                isAnimalCategory = false,
                isEventCategory = false,
            ),
            NewsPreviewItem(
                id = 1,
                imageUrl = "https://user-images.githubusercontent.com/90380451/148293903-d865011e-b5c7-4c7d-a74b-7bdf6ef8e54f.png",
                title = "Конкурс по вокальному пению в детском доме №6",
                abbreviatedText = "Дубовская школа-интернат для детей с ограниченными возможностями здоровья стала первой в области …",
                date = 1610755200000,
                isChildrenCategory = true,
                isAdultCategory = false,
                isElderlyCategory = false,
                isAnimalCategory = false,
                isEventCategory = true,
            ),
            NewsPreviewItem(
                id = 2,
                imageUrl = "https://user-images.githubusercontent.com/90380451/148643334-07754b36-85ec-4bc8-968e-ca3c457c6321.jpg",
                title = "Выставка бездомных животных",
                abbreviatedText = "Сегодня сотни собак и кошек живут в приютах в ожидании любящего и заботливого хозяина. Для того чтобы хвостатые …",
                date = 1609718400000,
                isChildrenCategory = false,
                isAdultCategory = false,
                isElderlyCategory = false,
                isAnimalCategory = true,
                isEventCategory = true,
            ),
            NewsPreviewItem(
                id = 3,
                imageUrl = "https://user-images.githubusercontent.com/90380451/148643672-dabdf82e-91a7-496f-a5fd-b2c663e7b10b.jpg",
                title = "Фестиваль творчества детей с ограниченными возможностями",
                abbreviatedText = "Проект позволит детям с инвалидностью открыться для всего остального мира и чувствовать себя полноценными жителями современного общества …",
                date = 1640217600000,
                isChildrenCategory = true,
                isAdultCategory = false,
                isElderlyCategory = false,
                isAnimalCategory = false,
                isEventCategory = true,
            )
        )
    }

}
