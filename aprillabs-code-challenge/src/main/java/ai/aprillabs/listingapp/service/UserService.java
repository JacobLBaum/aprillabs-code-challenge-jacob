package ai.aprillabs.listingapp.service;

import ai.aprillabs.listingapp.domain.User;
import ai.aprillabs.listingapp.domain.UserType;
import ai.aprillabs.listingapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    /**
     * The one parameter constructor for this user service
     * @param userRepository the UserRepository used in this user service
     */
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Adds a user to this user service
     * @param user the user to be added
     * @return the user that was added
     */
    public User createUser(User user) {
        return userRepository.save(user);
    }

    /**
     * Retrieves a specified user based on the ID
     * @param id the ID of the desired user
     * @return an optional containing the specified user
     */
    public Optional<User> getUser(Integer id) {
        return userRepository.findById(id);
    }

    /**
     * Deletes a specified user based on the ID from this user service
     * @param id the ID of the desired user to be deleted
     * @return true if the user was deleted, false if no user has the specified ID
     */
    public Boolean deleteUser(Integer id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    /**
     * Retrieves all the users in this user service
     * @return a list of optionals containing the users in this user service
     */
    public List<Optional<User>> getUsers() {
        ArrayList<Optional<User>> users = new ArrayList();
        for (User user: userRepository.findAll()) {
            users.add(Optional.of(user));
        }
        return users;
    }

    /**
     * Gets the discount rate for a specified user based on the ID
     * @param id the ID of the desired user
     * @return the discount rate of the specified user
     */
    public Integer getDiscountRateForUser(Integer id) {
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isEmpty()) {
            return 0;
        }
        User user = userOpt.get();

        /**
         * Contractors & Partners
         *      -under 25
         *          - > 10 referrals: 20%
         *          - < 10 referrals: 10%
         *      -25 and over: 5%
         * Agents
         *      -under 25: 10%
         *      -25 and over: 20%
         * Landlord: 30%
         * Broker: 40%
         */
        if (UserType.CONTRACTOR.equals(user.getType()) || UserType.PARTNER.equals(user.getType())) {
            if (user.getAge() < 25) {
                if (user.getNumOfReferral() > 10) {
                    return 20;
                } else {
                    return 10;
                }
            } else {
                return 5;
            }
        } else if (UserType.AGENT.equals(user.getType())) {
            if (user.getAge() < 25) {
                return 10;
            } else {
                return 20;
            }
        } else if (UserType.LANDLORD.equals(user.getType())) {
            return 30;
        } else if (UserType.BROKER.equals(user.getType())) {
            return 40;
        }
        return 0;
    }

    /**
     * Creates string representation of the users in this user service
     * @return a string of with all this users service's users
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (User user: userRepository.findAll()) {
            sb.append(user + "\n");
        }
        return sb.toString();
    }
}
