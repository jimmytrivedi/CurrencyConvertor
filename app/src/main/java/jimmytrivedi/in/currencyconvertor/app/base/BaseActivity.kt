package jimmytrivedi.`in`.currencyconvertor.app.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import jimmytrivedi.`in`.currencyconvertor.R
import jimmytrivedi.`in`.currencyconvertor.global.utility.AppConstant

abstract class BaseActivity: AppCompatActivity() {

    /** ----------------------------------- Views ----------------------------------- **/


    /** ----------------------------------- Object ----------------------------------- **/


    /** ----------------------------------- Primitive variables ----------------------------------- **/


    /** ----------------------------------- Abstract functions declaration ----------------------------------- **/
    protected abstract fun init()

    protected abstract fun initObservers()

    protected abstract fun getBundleParameters(bundle: Bundle)


    /** ----------------------------------- Listener / Receiver implementation ----------------------------------- **/


    /** ----------------------------------- overridden functions ----------------------------------- **/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        intent.extras?.let {
            //If the @arguments is null, then no need to give invocation of below function to child classes
            getBundleParameters(it)
        }
        initObservers()
        init()
    }


    /** ----------------------------------- open functions ----------------------------------- **/
    /**
     * Fragment navigation method
     *
     * @param containerViewId :id
     * @param fragment        : navigate to screen
     * @param addToBackStack  : adding to the backstack
     * @param style           : animation style
     */
    fun fragmentTransaction(containerViewId: Int, fragment: Fragment?, addToBackStack: Boolean, style: Int) {
        fragment?.let {
            if (isFinishing) {
                return
            }
            val fragmentManager = supportFragmentManager
            val transaction = fragmentManager.beginTransaction()
            when (style) {
                AppConstant.Animation.FADE_ANIMATION -> {
                    transaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                    transaction.replace(containerViewId, it, it.javaClass.simpleName)
                }
                AppConstant.Animation.LEFT_TO_RIGHT -> {
                    transaction.setCustomAnimations(R.anim.activity_in, R.anim.activity_out, R.anim.activity_back_in, R.anim.activity_back_out)
                    transaction.replace(containerViewId, it, it.javaClass.simpleName)
                }
                AppConstant.Animation.BOTTOM_TO_TOP -> {
                    transaction.setCustomAnimations(R.anim.anim_slide_up, R.anim.anim_fade_out, R.anim.anim_fade_in, R.anim.anim_slide_down)
                    transaction.replace(containerViewId, it, it.javaClass.simpleName)
                }
                else -> transaction.replace(containerViewId, it, it.javaClass.simpleName)
            }
            if (addToBackStack) {
                transaction.addToBackStack(it.javaClass.simpleName)
            }

            // replace with commitAllowingStateLoss() check reference for more  http://stackoverflow.com/questions/17184653/commitallowingstateloss-in-fragment-activities
            transaction.commitAllowingStateLoss()
        }
    }


    /** ----------------------------------- public functions ----------------------------------- **/


    /** ----------------------------------- private functions ----------------------------------- **/
}