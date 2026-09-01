package com.bilal.store.repositories;

import com.bilal.store.dtos.UserSummary;
import com.bilal.store.entities.Profile;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProfileRepository extends CrudRepository<Profile, Long> {

//    @EntityGraph(attributePaths = "user")
//    @Query("select u.id as id, u.email as email from User u where u.profile.loyaltyPoints > :loyaltyPoints order by u.email")
//    List<UserSummary> findProfilesWithLoyaltyPointsGreaterThan(@Param("loyaltyPoints") Integer loyaltyPoints);

}
