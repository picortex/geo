@file:Suppress("NON_EXPORTABLE_TYPE")

package geo

import kotlinx.collections.interoperable.List
import kotlinx.serialization.Serializable
import kotlin.js.JsExport


@JsExport
@Serializable
data class GeoLocation(
    val lines: List<String>,
    val country: String,
    val coords: LatLng? = null,
    val code: String? = null
) {
    val address get() = lines.joinToString(", ")
    val addressMultiline get() = lines.joinToString("\n")
}
