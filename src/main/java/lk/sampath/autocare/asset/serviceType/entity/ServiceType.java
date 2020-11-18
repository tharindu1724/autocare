package lk.sampath.autocare.asset.serviceType.entity;

import com.fasterxml.jackson.annotation.JsonFilter;

import lk.sampath.autocare.asset.serviceTypeParameter.entity.ServiceTypeParameter;
import lk.sampath.autocare.asset.vehicle.entity.Enum.VehicleModel;
import lk.sampath.autocare.util.audit.AuditEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonFilter("ServiceType")
public class ServiceType extends AuditEntity {

    private String name;

    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private VehicleModel vehicleModel;


    @ManyToMany
    @JoinTable(name = "service_type_service_type_parameter",
            joinColumns = @JoinColumn(name = "service_type_id"),
            inverseJoinColumns = @JoinColumn(name = "service_type_parameter_id"))
    private List< ServiceTypeParameter > serviceTypeParameters;
}
