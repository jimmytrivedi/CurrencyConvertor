package jimmytrivedi.`in`.currencycalculator.base

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

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


    /** ----------------------------------- public functions ----------------------------------- **/


    /** ----------------------------------- private functions ----------------------------------- **/
}