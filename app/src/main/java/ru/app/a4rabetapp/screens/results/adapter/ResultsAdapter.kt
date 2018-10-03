package ru.app.a4rabetapp.screens.results.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import ru.app.a4rabetapp.base.DelegateAdapter
import ru.app.a4rabetapp.models.ResultModel
import ru.app.a4rabetapp.models.TitleModel

class ResultsAdapter(val delegateAdapter: DelegateAdapter) : ResultsAdapterImpl {

    override fun removeItems(items: List<*>) {
        delegateAdapter.removeItems(items)
    }

    override fun addItems(items: List<*>) {
        delegateAdapter.addItems(items)
    }

    override fun clear() {
        delegateAdapter.clearItems()
    }

    override fun removeItem(item: Any) {
        delegateAdapter.removeItem(item)
    }

    override fun addItems(pos: Int, items: List<*>) {
        delegateAdapter.addItems(pos, items)
    }

    override fun addItem(pos: Int, item: Any) {
        delegateAdapter.addItem(pos, item)
    }

    override fun size(): Int {
        return delegateAdapter.itemCount
    }

    override fun getItem(pos: Int): Any {
        return delegateAdapter.getItem(pos)
    }

    override fun getRecyclerViewAdapter(): RecyclerView.Adapter<*> {
        return delegateAdapter
    }
}

class ResultsBuilder : ResultsBuilderImpl {

    var recyclerView: RecyclerView? = null
    val builder = DelegateAdapter.Builder()
    var onClick: (View) -> Unit = {}

    override fun setRecycler(recyclerView: RecyclerView) {
        this.recyclerView = recyclerView
    }

    override fun setOnClickListener(onClickListener: View.OnClickListener) {
        onClick = { onClickListener.onClick(it) }
    }

    override fun build(): ResultsAdapterImpl {
        return ResultsAdapter(createDelegateAdapter())
    }

    private fun createDelegateAdapter(): DelegateAdapter {
        addResultsDelegate()
        addTitleDelegate()
        return builder.build()
    }

    private fun addTitleDelegate() {
        builder.addDelegate(TitleModel::class.java, TitleDelegate())
    }

    private fun addResultsDelegate() {
        builder.addDelegate(ResultModel::class.java, ResultDelegate())
    }

}
