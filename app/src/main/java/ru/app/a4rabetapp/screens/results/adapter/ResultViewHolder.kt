package ru.app.a4rabetapp.screens.results.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import ru.app.a4rabetapp.R
import ru.app.a4rabetapp.models.ResultConstants
import ru.app.a4rabetapp.models.ResultModel
import kotlinx.android.synthetic.main.row_result.view.*

class ResultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    fun bind(model: ResultModel) {
        itemView.title.text = model.title
        itemView.description.text = model.description
        itemView.result.text = model.result
        when (model.status) {
            ResultConstants.IN_PROGRESS -> inProgressStyle(itemView)
            ResultConstants.LOSS -> inLossStyle(itemView)
            ResultConstants.WIN -> inWinStyle(itemView)
        }
    }

    private fun inWinStyle(itemView: View) {
        itemView.root_Layout.setBackgroundResource(R.color.green)
    }

    private fun inLossStyle(itemView: View) {
        itemView.root_Layout.setBackgroundResource(R.color.red)
    }

    private fun inProgressStyle(itemView: View) {
        itemView.root_Layout.setBackgroundResource(R.color.white_row)
    }
}
