package jimmytrivedi.`in`.currencyconvertor.app.base

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity


abstract class BaseFragment: Fragment() {

    /** ----------------------------------- Object ----------------------------------- **/
    private var mFragmentActivity: FragmentActivity? = null
    private var mContext: Context? = null


    /** ----------------------------------- Primitive variables ----------------------------------- **/
    private var hasInitializedRootView = false


    /** ----------------------------------- Abstract functions declaration ----------------------------------- **/
    protected abstract fun init()

    protected abstract fun initViews(view: View)

    protected abstract fun initObservers()

    protected abstract fun getBundleParameters(bundle: Bundle)


    /** ----------------------------------- overridden functions ----------------------------------- **/
    override fun onAttach(context: Context) {
        super.onAttach(context)
        setLocalContext(context)
        setLocalActivity(context as? FragmentActivity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        arguments?.let {
            //If the @arguments is null, then no need to give invocation of below function to child classes
            getBundleParameters(it)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!hasInitializedRootView) {
            hasInitializedRootView = true
            initObservers() //This has to be done once view is created
            initViews(view)
            init()
        }
    }

    /**
     * We don't want to call init() again on the destroying of view without re-attach again, that's why "hasInitializedRootView" we're
     * calling from here, and not from onDestroyView()
     */
    override fun onDetach() {
        super.onDetach()
        hasInitializedRootView = false
    }



    /** ----------------------------------- private functions ----------------------------------- **/
    private fun setLocalActivity(activity: FragmentActivity?) {
        mFragmentActivity = activity
    }

    private fun setLocalContext(context: Context?) {
        mContext = context
    }



    /** ----------------------------------- public functions ----------------------------------- **/
    /**
     * This method is used to get object of parent activity in current fragment.
     *
     * @return FragmentActivity
     */
    fun getLocalActivity(): FragmentActivity? {
        if (mFragmentActivity != null) {
            return mFragmentActivity
        } else {
            return activity
        }
    }

    fun getLocalContext(): Context? {
        if (mContext != null) {
            return mContext
        } else {
            return context
        }
    }

    /**
     * fragment backstack navigation
     */
    protected fun popBackStack() {
        getLocalActivity()?.let {
            val manager = it.supportFragmentManager
            if (manager.backStackEntryCount != 0) {
                if (!manager.isStateSaved) {
                    manager.popBackStackImmediate()
                }
            } else {
                it.finish()
            }
        }
    }
}