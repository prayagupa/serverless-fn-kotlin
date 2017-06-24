package event.handlers

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import java.util.*

class InventoryEventHandler : RequestHandler<Int, String> {

    override fun handleRequest(eventId: Int?, context: Context): String {
        return "{\"message\" : \"event processed\"}"
    }

    companion object {

        @JvmStatic fun main(event: Array<String>) {
            println("[INFO] received event " + Arrays.toString(event))
        }
    }
}
