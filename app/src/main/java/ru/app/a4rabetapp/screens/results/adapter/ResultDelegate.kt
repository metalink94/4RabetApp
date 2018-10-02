package ru.app.a4rabetapp.screens.results.adapter

import android.view.ViewGroup
import org.jetbrains.anko.layoutInflater
import ru.app.a4rabetapp.R
import ru.app.a4rabetapp.base.DelegateAdapter
import ru.app.a4rabetapp.models.ResultModel

class ResultDelegate: DelegateAdapter.Delegate<ResultModel, ResultViewHolder>() {

    override fun createViewHolder(parent: ViewGroup): ResultViewHolder {
        val view = parent.context.layoutInflater.inflate(R.layout.row_result, parent, false)
        return ResultViewHolder(view)
    }

    override fun bind(viewHolder: ResultViewHolder, model: ResultModel) {
        viewHolder.bind(model)
    }

}
