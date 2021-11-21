package eu.svoni.qrcoder.service;

import eu.svoni.qrcoder.model.QrCard;

public interface QrCardService {

    QrCard save(QrCard qrCard);
    QrCard get(Long id);
    void delete(Long id);
}
