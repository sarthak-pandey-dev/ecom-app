package com.wishmedia.ecom_app;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService {


    private final EcomRepository ecomRepo;

    public List<User> find_all()
    {
      return   ecomRepo.findAll();
    }

    public void addUser(User user)
    {
        ecomRepo.save(user);
    }

    public Optional<User> getUser(Long id)
    {
        return ecomRepo.findById(id);
    }


    public boolean updateUser(Long id , User user)
    {
        return ecomRepo.findById(id).map(existingUser ->
        {existingUser.setFirst_name(user.getFirst_name());
            existingUser.setLast_name(user.getLast_name());
            ecomRepo.save(existingUser);
            return true;}).orElse(false);


    }

}
