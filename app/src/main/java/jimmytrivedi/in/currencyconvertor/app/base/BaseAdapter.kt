package jimmytrivedi.`in`.currencyconvertor.app.base

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import dagger.hilt.android.internal.managers.FragmentComponentManager

/**
 * This is a Base class which provides Recyclerview-Adapter class functionality for entire project
 */
abstract class BaseAdapter : RecyclerView.Adapter<BaseViewHolder>() {

    /** --------------------------- abstract functions --------------------------- **/
    abstract fun onCreateBaseViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder

    abstract fun onBindBaseViewHolder(holder: BaseViewHolder, position: Int)

    abstract fun getItemBaseCount(): Int

    abstract fun getViewBinding(): ViewBinding



    /** --------------------------- overridden & default functions --------------------------- **/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val holder = onCreateBaseViewHolder(parent, viewType)
    //    getViewBinding().root.tag = viewType
        return holder
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        return onBindBaseViewHolder(holder, position)
    }

    override fun getItemCount(): Int {
        return getItemBaseCount()
    }

    /**
     * Initialize the binding and return it from the fun
     */
    fun setViewBinding(parent: ViewGroup, layoutRes: Int): ViewBinding {
        return DataBindingUtil.inflate(LayoutInflater.from(parent.context), layoutRes, parent, false)
    }

    /**
     * This fun is specifically written in this base class so that all the child adapter class in entire project doesn't need to suppress this
     * below warning in each file:
     * @SuppressLint("NotifyDataSetChanged")
     */
    @SuppressLint("NotifyDataSetChanged")
    fun notifyBaseDataSetChanged() {
        notifyDataSetChanged()
    }

    /**
     * This works from onCreateViewHolder
     */
    fun getContext(parent: ViewGroup): Context {
        return FragmentComponentManager.findActivity(parent.context)
    }

    /**
     * This works from onBindViewHolder
     */
    fun getContext(itemView: View): Context {
        return FragmentComponentManager.findActivity(itemView.context)
    }
}