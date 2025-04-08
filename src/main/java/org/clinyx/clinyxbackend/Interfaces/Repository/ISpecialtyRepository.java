package org.clinyx.clinyxbackend.Interfaces.Repository;

import org.clinyx.clinyxbackend.Entities.SpecialtyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISpecialtyRepository extends JpaRepository<SpecialtyEntity, Integer> {

}