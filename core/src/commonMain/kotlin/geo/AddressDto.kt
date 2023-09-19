@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package geo

import kollections.List
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
data class AddressDto(
    val country: Country,
    val entries: List<Entry>
)