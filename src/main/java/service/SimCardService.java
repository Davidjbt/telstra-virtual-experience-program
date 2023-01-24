package service;

import entity.SimCard;

public interface SimCardService {
    void save(SimCard simCard);
    SimCard findById(Long id);
}
