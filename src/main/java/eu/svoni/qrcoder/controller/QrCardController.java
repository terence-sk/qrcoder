package eu.svoni.qrcoder.controller;

import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import eu.svoni.qrcoder.dto.QrCardDto;
import eu.svoni.qrcoder.mapper.QrCardEntityDtoMapper;
import eu.svoni.qrcoder.model.QrCard;
import eu.svoni.qrcoder.service.QrCardService;

@RestController
@RequestMapping(value = "/qrcode")
public class QrCardController {

    private final QrCardService qrCardService;
    private final QrCardEntityDtoMapper qrCardMapper;

    public QrCardController(QrCardService qrCardService,
                            QrCardEntityDtoMapper qrCardMapper) {
        this.qrCardService = qrCardService;
        this.qrCardMapper = qrCardMapper;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<QrCardDto> createQrCode(@RequestBody QrCardDto value) {
        QrCard qrCard = qrCardService.save(qrCardMapper.dtoToEntity(value));
        return status(HttpStatus.CREATED).body(qrCardMapper.entityToDto(qrCard));
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<QrCardDto> getQrCode(@PathVariable("id") Long id) {
        QrCard qrCard = qrCardService.get(id);
        return ok(qrCardMapper.entityToDto(qrCard));
    }

    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteQrCode(@PathVariable("id") Long id) {
        qrCardService.delete(id);
        return status(HttpStatus.OK).build();
    }

    @PatchMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<QrCardDto> updateQrCode(@PathVariable("id") Long id,
                                                  @RequestBody QrCardDto value) {
        QrCard qrCard = qrCardService.get(id);
        qrCardMapper.updateEntity(qrCard, value);
        qrCardService.save(qrCard);
        return ok(qrCardMapper.entityToDto(qrCard));
    }

}
