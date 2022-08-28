package geo.internal

import geo.LatLng

internal data class LatLngImpl(
    override val lat: Double,
    override val lng: Double
) : LatLng {
    override fun toString(): String = "($lat, $lng)"
}