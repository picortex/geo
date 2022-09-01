package geo.countries

import Resource
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction
import java.io.File
import java.util.ResourceBundle

open class GenerateCountriesTask : DefaultTask() {
    private val serializer = Json {
        ignoreUnknownKeys = true
    }

    @OutputDirectory
    val outputDir: File = File(project.buildDir, "generated/geo/countries/kotlin")

    @TaskAction
    fun doGeneration() {
        val json = Resource::class.java.getResource("countries.json").readText()

        val unsupportedCountries = listOf("Tonga", "Sao Tome and Principe")
        val countries = serializer.decodeFromString<Set<RawCountry>>(json).filterNot {
            unsupportedCountries.contains(it.name)
        }

        if (!outputDir.exists()) outputDir.mkdirs()

        val countryKt = File(outputDir, "Country.kt")
        if (!countryKt.exists()) countryKt.createNewFile()
        countryKt.writeText(
            """
            /*
             * This code is generated
            **/
            @file:Suppress("unused")
                
            package geo
            
            import kash.Currency
            import kash.Currency.*
            import kotlin.js.JsExport
            import kotlinx.serialization.Serializable
            
            @JsExport
            @Serializable
            enum class Country(val code: String, val label: String, val currency: Currency, val isoCode: String, val dialingCode: String) {
        """.trimIndent()
        )

        val body = countries.joinToString(prefix = "\n", separator = ",\n\n", postfix = "\n") { it.toEnumLine() }
        countryKt.appendText(body)
        countryKt.appendText("}")
    }

    private fun RawCountry.toEnumLine() = buildString {
        append("\t")
        append("/** $name */ \n")
        append("\t$code(")
        append(""""$code","$name",${currency.code},"$isoCode","$dialingCode"""")
        append(")")
    }
}