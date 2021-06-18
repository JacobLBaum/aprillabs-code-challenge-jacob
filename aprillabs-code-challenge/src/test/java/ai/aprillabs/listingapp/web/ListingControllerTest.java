package ai.aprillabs.listingapp.web;

import ai.aprillabs.listingapp.domain.Listing;
import ai.aprillabs.listingapp.service.ListingService;
import ai.aprillabs.listingapp.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ListingController.class)
class ListingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ListingService listingService;


    @Test
    public void getListing() throws Exception {
        Listing listing = new Listing(1, "5th avenue", 2, 1, new BigDecimal(2500));
        when(listingService.getListing(1)).thenReturn(Optional.of(listing));
        this.mockMvc.perform(get("/listings/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(new ObjectMapper().writeValueAsString(listing)));
    }

    @Test
    public void getListingNotExists() throws Exception {
        when(listingService.getListing(1)).thenReturn(Optional.empty());
        this.mockMvc.perform(get("/listings/1"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$").doesNotExist());
    }

    @Test
    public void getListingInputNotValid() throws Exception {
        this.mockMvc.perform(get("/listings/abc"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$").doesNotExist());
    }
}