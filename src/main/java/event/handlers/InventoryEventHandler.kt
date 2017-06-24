package event.handlers

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestHandler
import org.json.JSONObject
import java.util.*

class InventoryEventHandler : RequestHandler<Int, String> {

    override public fun handleRequest(eventId: Int?, context: Context): String {
        val json = JSONObject()
        json.put("eventId", eventId)
        json.put("message", "SUCCESS")

        return json.toString()
    }

    companion object {

        @JvmStatic fun main(event: Array<String>) {
            println("[INFO] received event " + Arrays.toString(event))
        }
    }
}
