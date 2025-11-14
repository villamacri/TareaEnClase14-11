package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "monuments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Monument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String codPais, nombrePais, nombreCiudad, nombreMonumento, descripcion, urlFoto;
    private double latitud, longitud;


}
