package at.htlstp.geography.api;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

    @Nested
    class FindingByCountryCode {

        @Test
        void works() throws Exception {
            mockMvc
                    .perform(get("/countries/AT"))
                    .andExpect(status().isOk())
                    .andExpect(content().json(
                            """
                                    {
                                      "code": "AT",
                                      "name": "Austria"
                                    }
                                    """
                    ));
        }

        @Test
        void fails_for_invalid_country_code() throws Exception {
            mockMvc
                    .perform(get("/countries/404"))
                    .andExpect(status().isNotFound());
        }
    }

    @Nested
    class FindingCities {

        @Test
        void returns_all_without_query_params() throws Exception {
            mockMvc
                    .perform(get("/cities"))
                    .andExpect(status().isOk())
                    .andExpect(content().json(
                            """
                                    [
                                      {
                                        "name": "Gramais",
                                        "population": 41
                                      },
                                      {
                                        "name": "Linz",
                                        "population": 203012
                                      },
                                      {
                                        "name": "Berlin",
                                        "population": 3677472
                                      },
                                      {
                                        "name": "Vienna",
                                        "population": 1897491
                                      },
                                      {
                                        "name": "Hamburg",
                                        "population": 1906411
                                      },
                                      {
                                        "name": "Munich",
                                        "population": 1487708
                                      }
                                    ]
                                    """
                    ));
        }

        @Test
        void returns_all_from_given_country() throws Exception {
            mockMvc
                    .perform(get("/cities?country=AT"))
                    .andExpect(status().isOk())
                    .andExpect(content().json(
                            """
                                    [
                                      {
                                        "name": "Gramais"
                                      },
                                      {
                                        "name": "Linz"
                                      },
                                      {
                                        "name": "Vienna"
                                      }
                                    ]
                                    """
                    ));
        }


        @Test
        void returns_all_bigger_cities() throws Exception {
            mockMvc
                    .perform(get("/cities?min=1897491"))
                    .andExpect(status().isOk())
                    .andExpect(content().json(
                            """
                                    [
                                      {
                                        "name": "Berlin",
                                        "population": 3677472
                                      },
                                      {
                                        "name": "Vienna",
                                        "population": 1897491
                                      },
                                      {
                                        "name": "Hamburg",
                                        "population": 1906411
                                      }
                                    ]
                                    """
                    ));
        }

        @Test
        void returns_all_smaller_cities() throws Exception {
            mockMvc
                    .perform(get("/cities?max=203012"))
                    .andExpect(status().isOk())
                    .andExpect(content().json(
                            """
                                    [
                                      {
                                        "name": "Gramais",
                                        "population": 41
                                      },
                                      {
                                        "name": "Linz",
                                        "population": 203012
                                      }
                                    ]
                                    """
                    ));
        }

        @Test
        void returns_all_cities_from_country_in_range() throws Exception {
            mockMvc
                    .perform(get("/cities?country=DE&min=1487709&max=3677471"))
                    .andExpect(status().isOk())
                    .andExpect(content().json(
                            """
                                    [
                                      {
                                        "name": "Hamburg",
                                        "population": 1906411
                                      }
                                    ]
                                    """
                    ));
        }


    }

    @Nested
    class SavingCountry {

        @Test
        @DirtiesContext
        void works() throws Exception {
            var json = """
                    {
                      "code": "FR",
                      "name": "France"
                    }
                    """;
            var request = post("/countries")
                    .contentType(APPLICATION_JSON)
                    .content(json);

            var resource = "/countries/FR";
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
        void fails_if_country_already_exists() throws Exception {
            var json = """
                    {
                      "code": "AT",
                      "name": "Not Austria, has Kangaroos"
                    }
                    """;
            var request = post("/countries")
                    .contentType(APPLICATION_JSON)
                    .content(json);

            mockMvc.perform(request)
                    .andExpect(status().isBadRequest());
        }

    }
}



