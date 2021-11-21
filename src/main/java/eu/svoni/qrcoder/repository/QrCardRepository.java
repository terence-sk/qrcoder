package eu.svoni.qrcoder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import eu.svoni.qrcoder.model.QrCard;

@Repository
public interface QrCardRepository extends JpaRepository<QrCard, Long> {
    QrCard getById(@NonNull Long id);
}
