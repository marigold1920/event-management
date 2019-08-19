package group2.candidates.builder;


import group2.candidates.model.data.Section;
import group2.candidates.tool.PoolService;
import lombok.Getter;
import lombok.Setter;

public class SectionBuilder {

    Section section;
    PoolService pool = PoolService.getPoolService();
    @Setter @Getter private boolean valid = true;
    /**
     * Build Section contains training information of candidates when they join in an event
     * @return SectionBuilder to use fluent syntax for continuing build other part of adapter (Section)
     * @see TrainingInformationBuilder
     */
    public TrainingInformationBuilder section() {

        return new TrainingInformationBuilder(section);
    }

    /**
     * Build POJO Section include: Candidate
     * @return SectionBuilder to use fluent syntax for continuing build other part of adapter (Section)
     * @see SectionCandidateBuilder
     */
    public SectionCandidateBuilder candidate() {
        return new SectionCandidateBuilder(section);
    }

    /**
     * This function will be create entity Event from EventAdapter
     * @return Event
     */
    public Section build() {
        return section;
    }
}
