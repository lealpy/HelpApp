package com.lealpy.simbirsoft_training.data.database.help

import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class HelpRepository @Inject constructor(
    private val helpDao: HelpDao
) {

    fun getAllHelpEntities() : Single<List<HelpEntity>> {
        return helpDao.getAllHelpEntities()
    }

    fun insertHelpEntities(helpEntities: List<HelpEntity>) : Completable {
        return helpDao.insertHelpEntities(helpEntities)
    }

}
