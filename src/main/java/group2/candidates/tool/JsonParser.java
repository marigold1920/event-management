package group2.candidates.tool;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import group2.candidates.adapter.EventAdapter;
import group2.candidates.model.data.Candidate;
import group2.candidates.model.data.Department;
import group2.candidates.model.data.Event;
import group2.candidates.model.data.Section;

import java.time.LocalDate;

public final class JsonParser {

    /**
     * Use to setting Gson library as default HttpMessageConverter
     * Base on method create() from com.google.gson.GsonBuilder
     * This function will generate  Gson by using GsonBuilder applied some register TypeAdapter
     * forSerializable and Deserializable, setting format for gson string after Serializable,
     * setting date time format for converting LocalDate and versa,...
     * @return Gson
     */
    public static Gson create() {

        return new GsonBuilder()
                    .setPrettyPrinting()
                    .registerTypeAdapter(Department.class, new DepartmentJsonSerializable())
//                    .registerTypeAdapter(EventAdapter.class, new EventAdapterDeserializable())
                    .registerTypeAdapter(Candidate.class, new CandidateJsonSerializable())
                    .registerTypeAdapter(Event.class, new EventJsonSerializable())
                    .registerTypeAdapter(Section.class, new SectionJsonSerializable())
                    .registerTypeAdapter(Section.class, new SectionJsonDeserializable())
                    .registerTypeAdapter(LocalDate.class, new LocalDateConverter())
                .create();
    }
}
