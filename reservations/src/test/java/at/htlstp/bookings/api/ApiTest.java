package at.htlstp.bookings.api;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Testet die verlangten Rest-Endpoints.
 * Zum Debuggen empfielt es sich, nach dem perform .andDo(print()) einzubauen
 */
@SpringBootTest
@AutoConfigureMockMvc
class ApiTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void finding_by_guest_name_works() throws Exception {
        mockMvc
                .perform(get("/reservations?name=Alfred"))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        """
                                [
                                  {
                                    "id": 3,
                                    "table": {
                                      "id": 3,
                                      "size": 2
                                    },
                                    "guest": {
                                      "id": 1,
                                      "name": "Alfred"
                                    },
                                    "time": "2030-08-01T18:00:00",
                                    "groupSize": 1
                                  }
                                ]
                                """
                ));
    }

    @Nested
    class FindingById {

        @Test
        void works() throws Exception {
            mockMvc
                    .perform(get("/reservations/1"))
                    .andExpect(status().isOk())
                    .andExpect(content().json("""
                            {
                                "id": 1,
                                "table": {
                                  "id": 1,
                                  "size": 6
                                },
                                "guest": {
                                  "id": 3,
                                  "name": "Christina"
                                },
                                "time": "2030-08-01T18:00:00",
                                "groupSize": 2
                              }
                            """));
        }

        @Test
        void fails_for_invalid_reservation_id() throws Exception {
            mockMvc
                    .perform(get("/reservations/404"))
                    .andExpect(status().isNotFound());
        }
    }

    @Nested
    class Saving {

        @ParameterizedTest
        @DirtiesContext
        @ValueSource(strings = {"16:00:00", "20:00:00"})
            // der table ist gebucht für 18:00
        void works(String time) throws Exception {
            var json = """
                    {
                          "table": {
                            "id": 1
                          },
                          "guest": {
                            "id": 1
                          },
                          "groupSize": 6,
                          "time": "2030-08-01T%s"
                        }
                    """.formatted(time);
            var request = post("/reservations")
                    .contentType(APPLICATION_JSON)
                    .content(json);

            var resource = "/reservations/5";
            mockMvc.perform(request)
                    .andExpect(status().isCreated())
                    .andExpect(header().string("Location", "http://localhost" + resource))
                    .andExpect(content().json(json)).andReturn();
            mockMvc.perform(get(resource))
                    .andExpect(status().isOk())
                    .andExpect(content().json(json));
        }

        @Test
        @DirtiesContext
        void fails_if_group_size_exceeds_table_size() throws Exception {
            var json = """
                    {
                          "table": {
                            "id": 3
                          },
                          "guest": {
                            "id": 1
                          },
                          "time": "2030-10-01T18:00:00",
                          "groupSize": 3
                        }
                        """;
            var request = post("/reservations")
                    .contentType(APPLICATION_JSON)
                    .content(json);

            mockMvc.perform(request)
                    .andExpect(status().isBadRequest());
        }

        @ParameterizedTest
        @DirtiesContext
        @ValueSource(strings = {"16:00:01", "19:59:59"})
            // der table ist gebucht für 18:00
        void fails_if_table_has_booking_within_2_hours(String time) throws Exception {
            var json = """
                    {
                          "table": {
                            "id": 1
                          },
                          "guest": {
                            "id": 1
                          },
                          "groupSize": 1,
                          "time": "2030-08-01T%s"
                        }
                    """.formatted(time);
            var request = post("/reservations")
                    .contentType(APPLICATION_JSON)
                    .content(json);

            mockMvc.perform(request)
                    .andExpect(status().isBadRequest());
        }
    }
}



