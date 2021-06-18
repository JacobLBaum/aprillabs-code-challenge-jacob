package ai.aprillabs.listingapp.service;

import ai.aprillabs.listingapp.domain.Listing;
import ai.aprillabs.listingapp.repository.ListingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ListingService {

    private final ListingRepository listingRepository;

    /**
     * The one parameter constructor for this listing service
     * @param listingRepository the ListingRepository used for this listing service
     */
    @Autowired
    public ListingService(ListingRepository listingRepository) {
        this.listingRepository = listingRepository;
    }

    /**
     * Saves a listing into this listing service
     * @param listing the listing to be saved
     * @return the listing that was saved
     */
    public Listing createListing(Listing listing) {
        return listingRepository.save(listing);
    }

    /**
     * Retrieves the specified listing from this listing service by its ID
     * @param id the id of the listing to be returned
     * @return an Optional containing the specified listing
     */
    public Optional<Listing> getListing(Integer id) {
        return listingRepository.findById(id);
    }

    /**
     * Deletes the specified listing from this listing service by its ID
     * @param id the id of the listing to be deleted
     * @return true if the listing was deleted, false if no listing in this listing service has the specified ID
     */
    public Boolean deleteListing(Integer id) {
        if (listingRepository.existsById(id)) {
            listingRepository.deleteById(id);
            return true;
        }
        return false;
    }

    /**
     * Returns all listings in this listing service
     * @return all listings in this listing service
     */
    public List<Optional<Listing>> getListings() {
        ArrayList<Optional<Listing>> listings = new ArrayList();
        for (Listing listing: listingRepository.findAll()) {
            listings.add(Optional.of(listing));
        }
        return listings;
    }

    /**
     * Creates string representation of the listings in this listing service
     * @return a string of with all this listing service's listings
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Listing listing: listingRepository.findAll()) {
            sb.append(listing + "\n");
        }
        return sb.toString();
    }
}
