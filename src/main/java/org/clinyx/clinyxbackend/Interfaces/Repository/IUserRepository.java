package org.clinyx.clinyxbackend.Interfaces.Repository;

import org.clinyx.clinyxbackend.Entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<UserEntity, Long> {
}