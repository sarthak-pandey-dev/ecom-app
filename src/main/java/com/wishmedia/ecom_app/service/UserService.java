package com.wishmedia.ecom_app.service;

import com.wishmedia.ecom_app.Repository.EcomRepository;
import com.wishmedia.ecom_app.dto.AddressDTO;
import com.wishmedia.ecom_app.dto.UserRequest;
import com.wishmedia.ecom_app.dto.UserResponse;
import com.wishmedia.ecom_app.model.Address;
import com.wishmedia.ecom_app.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserService {


    private final EcomRepository ecomRepo;

    public List<UserResponse> find_all()
    {
     // List<User>userList=  ecomRepo.findAll();
      return  ecomRepo.findAll().stream().
              map(this::mapTOUserResponse)
              .collect(Collectors.toList());
    }

    public void addUser(UserRequest user)
    {
        User user1=new User();
        updateUserFromRequest(user1,user);
        ecomRepo.save(user1);
    }



    public Optional<UserResponse> getUser(Long id)
    {
        return ecomRepo.findById(id)
                .map(this::mapTOUserResponse);
    }


    public boolean updateUser(Long id , UserRequest userRequest)
    {
        return ecomRepo.findById(id).map(existingUser ->
        {updateUserFromRequest(existingUser,userRequest);
        ecomRepo.save(existingUser);
        return true;}).orElse(false);


    }
    private void updateUserFromRequest(User user1, UserRequest user) {
user1.setFirst_name(user.getFirst_name());
user1.setLast_name(user.getLast_name());
user1.setEmail(user.getEmail());
user1.setPhone(user.getPhone());
if (user.getAddress()!=null)
{
    Address address=new Address();
    address.setStreet(user.getAddress().getStreet());
    address.setState(user.getAddress().getState());
    address.setZipcode(user.getAddress().getZipcode());
    address.setCity(user.getAddress().getCity());
    address.setCountry(user.getAddress().getCountry());
    user1.setAddress(address);
}

    }

    private  UserResponse mapTOUserResponse(User user)

    {
        UserResponse response=new UserResponse();
        response.setId(String.valueOf(user.getId()));
        response.setFirst_name(user.getFirst_name());
        response.setLast_name(user.getLast_name());
        response.setEmail(user.getEmail());
        response.setPhone(user.getPhone());
        response.setRole(user.getRole());

        if(user.getAddress()!=null)
        {
            AddressDTO addressDTO=new AddressDTO();
            addressDTO.setStreet(user.getAddress().getStreet());
            addressDTO.setCity(user.getAddress().getCity());
            addressDTO.setState(user.getAddress().getState());
            addressDTO.setCountry(user.getAddress().getCountry());
            addressDTO.setZipcode(user.getAddress().getZipcode());
            response.setAddress(addressDTO);
        }

        return response;
    }

}
