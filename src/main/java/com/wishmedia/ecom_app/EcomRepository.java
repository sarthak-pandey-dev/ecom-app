package com.wishmedia.ecom_app;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EcomRepository extends JpaRepository<User,Long> {
}
