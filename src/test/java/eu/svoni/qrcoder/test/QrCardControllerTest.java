package eu.svoni.qrcoder.test;

import static java.time.LocalDateTime.now;
import static java.util.UUID.randomUUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import eu.svoni.qrcoder.Main;
import eu.svoni.qrcoder.dto.QrCardDto;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

@SpringBootTest(classes = Main.class,
                webEnvironment = WebEnvironment.RANDOM_PORT)
@TestMethodOrder(OrderAnnotation.class)
public class QrCardControllerTest {

    @LocalServerPort
    private int port;

    WebTestClient webTestClient;
    ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        webTestClient = WebTestClient.bindToServer().baseUrl("http://localhost:" + port).build();
        objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    }

    @Test
    @Order(1)
    public void contextLoads() {
        assertThat(webTestClient).isNotNull();
    }

    @Test
    @Order(2)
    public void getCard_expectNotFound() {
        webTestClient.get()
                     .uri("/qrcode/1")
                     .accept(APPLICATION_JSON)
                     .header("Content-Type", APPLICATION_JSON_VALUE)
                     .exchange()
                     .expectStatus()
                     .isNotFound();
    }

    @Test
    @Order(3)
    public void createCard_expectCreated() throws JsonProcessingException {
        QrCardDto body = QrCardDto.builder()
                                  .uuid(randomUUID())
                                  .created(now())
                                  .modified(now())
                                  .validFrom(now())
                                  .validTo(now())
                                  .name("my card")
                                  .value("aaaxsdsadsads")
                                  .build();

        webTestClient.post()
                     .uri("/qrcode")
                     .bodyValue(objectMapper.writeValueAsString(body))
                     .accept(APPLICATION_JSON)
                     .header("Content-Type", APPLICATION_JSON_VALUE)
                     .exchange()
                     .expectStatus()
                     .isCreated();
    }

    @Test
    @Order(4)
    public void getCard_expectOk() {
        webTestClient.get()
                     .uri("/qrcode/1")
                     .accept(APPLICATION_JSON)
                     .header("Content-Type", APPLICATION_JSON_VALUE)
                     .exchange()
                     .expectStatus()
                     .isOk()
                     .expectBody()
                     .consumeWith(resp -> {
                         assertThat(resp.getResponseBody()).isNotNull();
                         String body = new String(resp.getResponseBody());
                         QrCardDto qr = convertToObject(body);
                         assertThat(qr.getName()).isEqualTo("my card");
                     });
    }

    @Test
    @Order(5)
    public void updateCard_expectOk() throws JsonProcessingException {
        QrCardDto body = QrCardDto.builder()
                                  .uuid(randomUUID())
                                  .created(now())
                                  .modified(now())
                                  .validFrom(now())
                                  .validTo(now())
                                  .name("my card updated")
                                  .value("aaaxsdsadsads")
                                  .build();

        webTestClient.patch()
                     .uri("/qrcode/1")
                     .bodyValue(objectMapper.writeValueAsString(body))
                     .accept(APPLICATION_JSON)
                     .header("Content-Type", APPLICATION_JSON_VALUE)
                     .exchange()
                     .expectStatus()
                     .isOk();
    }

    @Test
    @Order(6)
    public void getUpdatedCard_expectBodyChanged() {
        webTestClient.get()
                     .uri("/qrcode/1")
                     .accept(APPLICATION_JSON)
                     .header("Content-Type", APPLICATION_JSON_VALUE)
                     .exchange()
                     .expectStatus()
                     .isOk()
                     .expectBody()
                     .consumeWith(resp -> {
                         assertThat(resp.getResponseBody()).isNotNull();
                         String body = new String(resp.getResponseBody());
                         QrCardDto qr = convertToObject(body);
                         assertThat(qr.getName()).isEqualTo("my card updated");
                     });
    }

    private QrCardDto convertToObject(String json) {
        QrCardDto qrCardDto = new QrCardDto();
        try {
            qrCardDto = objectMapper.readValue(json, QrCardDto.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return qrCardDto;
    }

}
