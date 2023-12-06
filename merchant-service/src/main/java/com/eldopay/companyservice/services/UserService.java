package com.eldopay.companyservice.services;




//import org.springframework.security.crypto.password.PasswordEncoder;
import com.eldopay.companyservice.dtos.customer.CreateUserRequest;
import com.eldopay.companyservice.exceptions.UserException;
import com.eldopay.companyservice.models.Customer;
import com.eldopay.companyservice.models.uber.PassengerRide;
import com.eldopay.companyservice.models.wallet.Wallet;
import com.eldopay.companyservice.repository.CustomerRepository;
import com.eldopay.companyservice.repository.uber.DriverRepository;
import com.eldopay.companyservice.repository.wallet.WalletRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private CustomerRepository userRepository;
    private DriverRepository driverRepository;
    private WalletRepository walletRepository;

//    private JwtService jwtService;

//    private PasswordEncoder passwordEncoder;



    public List<PassengerRide> completedRides(Long userId) throws UserException {
        List<PassengerRide> completedRides = userRepository.getCompletedRide(userId);
        return completedRides;
    }

    public Customer findUserById(Long id) throws UserException {
        Optional<Customer> user =userRepository.findById(id);
        if(user.isPresent()){
            return user.get();
        }
      throw new UserException("user not found with id --"+id);
    }

    public Customer createUser(CreateUserRequest createUserRequest) throws UserException {
//        User user = new User();
        Customer user = new Customer();
//        String encodedPassword = passwordEncoder.encode(createUserRequest.getPassword());
        user.setUsername(createUserRequest.getFullName());
        user.setCreatedAt(LocalDateTime.now());
        user.setMobile(createUserRequest.getMobile());
        user.setEmail(createUserRequest.getEmail());
        Customer savedUser = userRepository.save(user);

        Wallet wallet = new Wallet();
        wallet.setAvailableBalance(BigDecimal.ZERO);
        wallet.setNumber(createUserRequest.getMobile());
        wallet.setCustomer(savedUser);
        walletRepository.save(wallet);
        return savedUser;    }

    public Customer findUserProfileByToken(String token) throws UserException {
//        String email = jwtProvider.getEmailFromToken(token);
//        String email = jwtService.extractUsername(token);
      Customer user = userRepository.findByEmail(email);

        if(user != null){
            return user;
        }

        throw new UserException("invalid token");
    }
}
