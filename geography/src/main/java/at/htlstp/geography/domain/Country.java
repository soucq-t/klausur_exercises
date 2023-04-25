package at.htlstp.geography.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "countries")
public class Country {

    @Id
    @NotNull
    @Size(max = 2,min = 2)
    private String code;

    @NotNull
    @Pattern(regexp = "^[A-Z][a-zA-Z]*$")
    @Column(name = "country_name")
    private String name;


}
