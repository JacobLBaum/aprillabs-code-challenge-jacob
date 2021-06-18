package ai.aprillabs.listingapp.web;

import ai.aprillabs.listingapp.domain.Listing;
import ai.aprillabs.listingapp.domain.User;
import ai.aprillabs.listingapp.service.ListingService;
import ai.aprillabs.listingapp.service.UserService;
import ai.aprillabs.listingapp.web.dto.request.CreateUserRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class ListingController {

    private final ListingService listingService;
    private final UserService userService;

    @Autowired
    public ListingController(ListingService listingService, UserService userService) {
        this.listingService = listingService;
        this.userService = userService;
    }

    /**
     * Handles the post requests to /listings by creating a new listing from the body of the message
     * @param listing the body of the message that is used as the listing object
     * @return the new listing object created
     */
    @PostMapping(path="/listings")
    public ResponseEntity<Listing> createListing(@Valid @RequestBody Listing listing) {
        listingService.createListing(listing);
        return new ResponseEntity<>(
                listing,
                HttpStatus.CREATED);
    }

    /**
     * Handles get requests to /listings
     * @return a list of all listings
     */
    @GetMapping(path="/listings")
    public ResponseEntity<List<Optional<Listing>>> getListings() {
        return new ResponseEntity<List<Optional<Listing>>>(listingService.getListings(), HttpStatus.OK);
    }

    /**
     * Handles get requests to /listings/{id} for a specific listing
     * @param id the id of the listing to be returned
     * @return the specified listing
     */
    @GetMapping(path="/listings/{id}")
    public ResponseEntity<Listing> getListing(@Valid @PathVariable Integer id) {
        return listingService.getListing(id)
                .map(listing -> new ResponseEntity<>(
                        listing,
                        HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Handles delete requests to /listings/{id} for a specific listing
     * @param id the id of the listing to be deleted
     * @return the result of the deletion
     */
    @DeleteMapping(path="/listings/{id}")
    public String deleteListing(@Valid @PathVariable Integer id) {
        if (listingService.deleteListing(id))
            return "Deleted: " + id;
        return "No listing with id: " + id;
    }

    /**
     * Handles post requests to /users by creating a new user from the body of the message
     * @param userRequestDto the data transfer object that turns the body of the message into a new user object
     * @return the new user object created
     */
    @PostMapping(path="/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody CreateUserRequestDto userRequestDto) {
        User createdUser = userRequestDto.toUser();
        userService.createUser(createdUser);
        return new ResponseEntity<>(
                createdUser,
                HttpStatus.CREATED);
    }

    /**
     * Handles the get requests to /users/{id} and returns the discount rate of the specified user
     * @param id the id of the desired user
     * @return the discount rate of the specified user
     */
    @GetMapping(path="/users/{id}")
    public ResponseEntity<Integer> getUserDiscountRate(@Valid @PathVariable Integer id) {
        return new ResponseEntity<Integer>(userService.getDiscountRateForUser(id), HttpStatus.OK);
    }
}
//I was not able to fully get the functionality of the user system working, the logic is there however something was
//causing the program to crash whenever a command involving creating a user or requesting a user's discount rate.
