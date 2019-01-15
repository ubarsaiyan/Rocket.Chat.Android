package chat.rocket.android.util.extension

import chat.rocket.android.core.lifecycle.CancelStrategy
import kotlinx.coroutines.*
import kotlin.coroutines.coroutineContext

/**
 * Launches a coroutine on the UI context.
 *
 * @param strategy a CancelStrategy for canceling the coroutine job
 */
fun launchUI(strategy: CancelStrategy, block: suspend CoroutineScope.() -> Unit): Job {
    return GlobalScope.launch(context = Dispatchers.Main + strategy.jobs, block = block)
}