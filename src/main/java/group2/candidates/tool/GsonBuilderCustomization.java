package group2.candidates.tool;

import com.google.gson.GsonBuilder;
import group2.candidates.adapter.EventAdapter;
import group2.candidates.model.data.Event;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class GsonBuilderCustomization {

    public GsonBuilder create() {
        return new GsonBuilder()
                    .setPrettyPrinting()
                    .registerTypeAdapter(Event.class, new EventJsonSerializable())
                    .registerTypeAdapter(EventAdapter.class, new EventAdapterDeserializable());
    }
}
