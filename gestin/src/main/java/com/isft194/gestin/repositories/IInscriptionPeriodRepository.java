package com.isft194.gestin.repositories;

import com.isft194.gestin.models.InscriptionPeriod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IInscriptionPeriodRepository extends IRepository<InscriptionPeriod, Long> {
}
