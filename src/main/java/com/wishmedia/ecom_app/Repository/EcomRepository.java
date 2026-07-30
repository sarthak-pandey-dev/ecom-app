package com.wishmedia.ecom_app.Repository;

import com.wishmedia.ecom_app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EcomRepository extends JpaRepository<User,Long> {
}
