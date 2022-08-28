import expect.expect
import geo.LatLng
import geo.LatLngAlt
import geo.serializers.LatLngAltSerializer
import geo.serializers.LatLngSerializer
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlin.test.Test

class LatLngAltSerializationTest {

    @Test
    fun should_serialize_a_lat_lng() {
        val cord = LatLngAlt(10.0, -10.0, 5.0)
        val json = """{"lat":10.0,"lng":-10.0,"alt":5.0}"""
        expect(Json.encodeToString(LatLngAltSerializer, cord)).toBe(json)
    }

    @Test
    fun should_deserialize_a_lat_lng() {
        val json = """{"lat":5.0,"lng":0.01,"alt":5.5}"""
        val cord = LatLngAlt(5.0, 0.01, 5.5)
        expect(Json.decodeFromString(LatLngAltSerializer, json)).toBe(cord)
    }
}