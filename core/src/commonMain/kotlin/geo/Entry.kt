@file:JsExport

package geo

import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
data class Entry(
    val label: String,
    val value: String?
)