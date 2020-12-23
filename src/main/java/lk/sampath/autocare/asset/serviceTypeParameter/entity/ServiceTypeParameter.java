package lk.sampath.autocare.asset.serviceTypeParameter.entity;

import com.fasterxml.jackson.annotation.JsonFilter;
import lk.sampath.autocare.asset.commonAsset.model.Enum.LiveDead;
import lk.sampath.autocare.asset.serviceType.entity.ServiceType;
import lk.sampath.autocare.util.audit.AuditEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonFilter("ServiceTypeParameter")
public class ServiceTypeParameter extends AuditEntity {

    private String name;

    private BigDecimal price;

    @Enumerated( EnumType.STRING)
    private LiveDead liveDead;

    @ManyToMany(mappedBy = "serviceTypeParameters")
    private List< ServiceType > serviceTypes;


}
