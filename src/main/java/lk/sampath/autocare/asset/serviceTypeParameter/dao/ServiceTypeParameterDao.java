package lk.sampath.autocare.asset.serviceTypeParameter.dao;


import lk.sampath.autocare.asset.serviceTypeParameter.entity.ServiceTypeParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceTypeParameterDao extends JpaRepository< ServiceTypeParameter, Integer> {

}