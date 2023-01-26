package com.isa.BloodBank.repository;

import com.isa.BloodBank.model.BloodBankCenter;
import com.isa.BloodBank.model.Address;
import com.isa.BloodBank.model.BloodSupply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BloodBankCenterRepository extends JpaRepository<BloodBankCenter, Integer> {
    BloodBankCenter findBloodBankCenterById(int id);
    Page<BloodBankCenter> findAll(Pageable pageable);

    @Query("select b from bloodBankCenters b, adresses adr where b.address.id = adr.id and lower(b.name) like %?1% and lower(adr.city) like %?2% and b.averageGrade >= ?3")
    Page<BloodBankCenter> findAllByNameAndCityAndRating(String searchName, String searchCity, Double filterByRating, Pageable pageable);

    @Query("select b from bloodBankCenters b, adresses adr where b.address.id = adr.id and lower(b.name) like %?1% and lower(adr.city) like %?2% and b.averageGrade >= ?3 order by b.name asc")
    Page<BloodBankCenter> findAllByNameAndCityAndRatingNameAsc(String searchName, String searchCity, Double filterByRating, Pageable pageable);

    @Query("select b from bloodBankCenters b, adresses adr where b.address.id = adr.id and lower(b.name) like %?1% and lower(adr.city) like %?2% and b.averageGrade >= ?3 order by b.name desc")
    Page<BloodBankCenter> findAllByNameAndCityAndRatingNameDesc(String searchName, String searchCity, Double filterByRating, Pageable pageable);

    @Query("select b from bloodBankCenters b, adresses adr where b.address.id = adr.id and lower(b.name) like %?1% and lower(adr.city) like %?2% and b.averageGrade >= ?3 order by b.address.city asc")
    Page<BloodBankCenter> findAllByNameAndCityAndRatingCityAsc(String searchName, String searchCity, Double filterByRating, Pageable pageable);

    @Query("select b from bloodBankCenters b, adresses adr where b.address.id = adr.id and lower(b.name) like %?1% and lower(adr.city) like %?2% and b.averageGrade >= ?3 order by b.address.city desc")
    Page<BloodBankCenter> findAllByNameAndCityAndRatingCityDesc(String searchName, String searchCity, Double filterByRating, Pageable pageable);

    @Query("select b from bloodBankCenters b, adresses adr where b.address.id = adr.id and lower(b.name) like %?1% and lower(adr.city) like %?2% and b.averageGrade >= ?3 order by b.averageGrade asc")
    Page<BloodBankCenter> findAllByNameAndCityAndRatingAvgGradeAsc(String searchName, String searchCity, Double filterByRating, Pageable pageable);

    @Query("select b from bloodBankCenters b, adresses adr where b.address.id = adr.id and lower(b.name) like %?1% and lower(adr.city) like %?2% and b.averageGrade >= ?3 order by b.averageGrade desc")
    Page<BloodBankCenter> findAllByNameAndCityAndRatingAvgGradeDesc(String searchName, String searchCity, Double filterByRating, Pageable pageable);

}