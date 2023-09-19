package geo

import geo.internal.AddressFieldImpl
import neat.ValidationFactory
import symphony.Changer
import symphony.Fields
import symphony.Visibility
import kotlin.reflect.KMutableProperty0

fun AddressField(
    property: KMutableProperty0<AddressOutput?>,
    label: String = property.name,
    visibility: Visibility = Visibility.Visible,
    hint: String = "Address",
    onChange: Changer<AddressOutput>? = null,
    factory: ValidationFactory<AddressOutput>? = null
) : AddressField = AddressFieldImpl(property, label, visibility, hint, onChange, factory)

fun Fields<Any>.address(
    property: KMutableProperty0<AddressOutput?>,
    label: String,
    visibility: Visibility,
    hint: String,
    onChange: Changer<AddressOutput>?,
    factory: ValidationFactory<AddressOutput>?
): AddressField = getOrCreate(property = property) {
    AddressFieldImpl(property, label, visibility, hint, onChange, factory)
}