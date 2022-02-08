package com.lealpy.simbirsoft_training.ui.help

interface AsyncTaskResponse {
    fun preExecute()
    fun postExecute(helpItemsResult: List<HelpItem>?)
}
