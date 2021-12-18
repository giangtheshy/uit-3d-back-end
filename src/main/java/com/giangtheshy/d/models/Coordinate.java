package com.giangtheshy.d.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.giangtheshy.d.models.Enums.TypeCoordinate;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "coordinate")
public class Coordinate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private TypeCoordinate type;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,mappedBy = "coordinate")
    private List<Part> parts;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "element_id", insertable = false, updatable = false)
    private Element element;

}
