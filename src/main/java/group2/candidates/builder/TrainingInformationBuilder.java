package group2.candidates.builder;

import group2.candidates.model.data.Event;
import group2.candidates.model.data.Section;

public class TrainingInformationBuilder extends SectionBuilder {

    TrainingInformationBuilder(Section section) {
        this.section = section;
    }

    /**
     * Build training information of candidates when they join in event
     * @param finalGrade grade of candidates when they complete their course
     * @param completionLevel level of completion
     * @param certificateId id of certificate when they get in course
     * @param note note about candidates when they joins in event
     * @param contractType type of contract
     * @return TrainingInformationBuilder
     */
    public TrainingInformationBuilder join(Event event, Integer sectionId, String status, String finalGrade, String completionLevel, String certificateId, String note, String contractType) {
        this.section = Section.builder()
                    .sectionId(sectionId)
                    .candidateStatus(status)
                    .contractType(contractType)
                    .finalGrade(finalGrade)
                    .completionLevel(completionLevel)
                    .certificatedId(certificateId)
                    .note(note)
                    .event(event)
                .build();

        return this;
    }
}
