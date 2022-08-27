package geo

import kotlin.js.JsExport

@JsExport
interface LatLng {
    val lat: Double
    val lng: Double
}