package jimmytrivedi.`in`.currencycalculator.di.qualifier

import javax.inject.Qualifier

/**
 * Default coroutine dispatcher
 */
@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class DefaultDispatcher

/**
 * IO coroutine dispatcher
 */
@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class IoDispatcher

/**
 * Main coroutine dispatcher
 */
@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class MainDispatcher

/**
 * MainImmediate coroutine dispatcher
 */
@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class MainImmediateDispatcher
