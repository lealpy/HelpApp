package com.lealpy.simbirsoft_training.database.help

import io.reactivex.Completable
import io.reactivex.Single

class HelpRepository(
    private val helpDao: HelpDao,
) {

    fun getAllHelpEntities() : Single<List<HelpEntity>> {
        return helpDao.getAllHelpEntities()
    }

    fun insertHelpEntities(helpEntities: List<HelpEntity>) : Completable {
        return helpDao.insertHelpEntities(helpEntities)
    }

}
