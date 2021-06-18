package ai.aprillabs.listingapp.service;

import ai.aprillabs.listingapp.domain.Listing;
import ai.aprillabs.listingapp.repository.ListingRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListingServiceTest {
    @Mock
    private ListingRepository listingRepository;

    @InjectMocks
    private ListingService listingService;

    @Test
    void createListing() {
        Listing listing = new Listing(1, "5th avenue", 2, 1, new BigDecimal(2500));
        when(listingService.createListing(listing)).thenReturn(listing);
        Listing result = listingService.createListing(listing);
        Assertions.assertEquals(listing, result);
    }

    @Test
    void getListing() {
        Listing listing = new Listing(1, "5th avenue", 2, 1, new BigDecimal(2500));
        when(listingService.getListing(1)).thenReturn(Optional.of(listing));
        Optional<Listing> result = listingService.getListing(1);
        Assertions.assertEquals(listing, result.get());
    }

    @Test
    void getListingNotFound() {
        when(listingService.getListing(1)).thenReturn(Optional.empty());
        Optional<Listing> result = listingService.getListing(1);
        Assertions.assertTrue(result.isEmpty());
    }
}