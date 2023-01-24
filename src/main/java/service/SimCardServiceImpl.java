package service;

import entity.SimCard;
import org.springframework.stereotype.Service;
import repository.SimCardRepository;

@Service
public class SimCardServiceImpl implements SimCardService {

    private final SimCardRepository simCardRepository;

    public SimCardServiceImpl(SimCardRepository simCardRepository) {
        this.simCardRepository = simCardRepository;
    }

    @Override
    public void save(SimCard simCard) {
        simCardRepository.save(simCard);
    }

    @Override
    public SimCard findById(Long id) {
        return simCardRepository.findById(id).orElse(null);
    }
}
