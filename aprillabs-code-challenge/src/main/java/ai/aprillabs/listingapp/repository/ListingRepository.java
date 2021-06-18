package ai.aprillabs.listingapp.repository;

import ai.aprillabs.listingapp.domain.Listing;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListingRepository extends CrudRepository<Listing, Integer> {
}
