package group2.candidates.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import group2.candidates.repository.EventRepository;

@Service
public class ReportService {

    private EventRepository repository;

    public void report() {
        repository.findAll();
    }

    /**
     * @param repository the repository to set
     */
    @Autowired
    public void setRepository(EventRepository repository) {
        this.repository = repository;
    }
}