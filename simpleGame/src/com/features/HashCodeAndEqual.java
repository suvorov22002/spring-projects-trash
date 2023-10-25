package com.features;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Date;

@Data
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class HashCodeAndEqual {

    String nom;
    String prenom;
    Date dateNaiss;
    boolean adulte;
    long id;

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 31).append(nom).append(prenom).append(id)
                .append(dateNaiss).append(adulte).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {

        if(this == obj) {
            return true;
        }

        if(obj == null) {
            return false;
        }

        if(getClass() != obj.getClass()) {
            return false;
        }

        HashCodeAndEqual other = (HashCodeAndEqual) obj;
        return new EqualsBuilder().append(adulte, other.adulte)
                .append(dateNaiss, other.dateNaiss).append(id, other.id)
                .append(nom, other.nom).append(prenom, other.prenom).isEquals();

    }

}
