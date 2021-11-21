package eu.svoni.qrcoder.service;

import org.springframework.stereotype.Service;

import eu.svoni.qrcoder.model.QrCard;
import eu.svoni.qrcoder.repository.QrCardRepository;

@Service
public class QrCardServiceImpl implements QrCardService {

    private final QrCardRepository repository;

    public QrCardServiceImpl(QrCardRepository repository) {
        this.repository = repository;
    }

    @Override
    public QrCard save(QrCard qrCard) {
        return repository.save(qrCard);
    }

    @Override
    public QrCard get(Long id) {
        return repository.getById(id);
    }

    @Override
    public void delete(Long id) {
        repository.delete(repository.getById(id));
    }


}
