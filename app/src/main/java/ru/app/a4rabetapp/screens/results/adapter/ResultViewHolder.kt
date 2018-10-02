package ru.app.a4rabetapp.screens.results.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.row_result.view.*
import ru.app.a4rabetapp.models.ResultModel

class ResultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    fun bind(model: ResultModel) {
        itemView.commands.text = String.format("%s\n%s", model.title, model.description)
    }
}
