package jimmytrivedi.`in`.currencyconvertor.global.utility

import android.util.Log
import jimmytrivedi.`in`.currencyconvertor.BuildConfig

object LogUtils {
    /**
     * Common filter tag for all the logs getting printed.
     */
    private const val tag = "CurrencyLogs"

    /**
     * Flag to check whether log shall be printed or not.
     */
    private val isLogEnabled = BuildConfig.LOG_VALUE && BuildConfig.BUILD_TYPE == AppConstant.Default.BUILD_TYPE_DEBUG

    /**
     * Called to print verbose category log.
     *
     * @param data data to be printed for debugging
     */
    fun v(data: Any?) {
        if (isLogEnabled) Log.v(tag, data.toString())
    }

    /**
     * Called to print debug category log.
     *
     * @param data data to be printed for debugging
     */
    fun d(data: Any?) {
        if (isLogEnabled) Log.d(tag, data.toString())
    }

    /**
     * Called to print information category log.
     *
     * @param data data to be printed for debugging
     */
    fun i(data: Any?) {
        if (isLogEnabled) Log.i(tag, data.toString())
    }

    /**
     * Called to print warning category log.
     *
     * @param data data to be printed for debugging
     */
    fun w(data: Any?) {
        if (isLogEnabled) Log.w(tag, data.toString())
    }

    /**
     * Called to print error category log.
     *
     * @param data data to be printed for debugging
     */
    fun e(data: Any?) {
        if (isLogEnabled) Log.e(tag, data.toString())
    }
}
