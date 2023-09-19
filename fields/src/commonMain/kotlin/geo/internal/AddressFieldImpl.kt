package geo.internal

import geo.AddressField
import geo.AddressManager
import geo.AddressOutput
import geo.Country
import kollections.iEmptyList
import neat.ValidationFactory
import symphony.Changer
import symphony.Option
import symphony.SingleChoiceField
import symphony.Visibility
import symphony.internal.BaseFieldImpl
import kotlin.reflect.KMutableProperty0

@PublishedApi
internal class AddressFieldImpl(
    private val property: KMutableProperty0<AddressOutput?>,
    label: String,
    visibility: Visibility,
    hint: String,
    private val onChange: Changer<AddressOutput>?,
    factory: ValidationFactory<AddressOutput>?
) : BaseFieldImpl<AddressOutput>(property, label, visibility, hint, onChange, factory), AddressField {

    private var _country = property.get()?.country

    private var manager = AddressManager()

    override val country by lazy {
        SingleChoiceField(
            name = ::_country,
            items = Country.values().toList(),
            mapper = { Option(label = it.label, value = it.name) },
            onChange = {
                val out = state.value.output ?: AddressOutput(null, iEmptyList())
                set(out.copy(country = it, entries = manager.entries(it)))
            }
        )
    }

    override val entries get() = state.value.output?.entries ?: iEmptyList()
}