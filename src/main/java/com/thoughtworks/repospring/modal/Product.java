package com.thoughtworks.repospring.modal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Entity
@Table(name = "product")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@GenericGenerator(name = "jpa-uuid", strategy = "uuid2")
public class Product {
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    private UUID id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Weight is mandatory")
    private String weight;

    @NotBlank(message = "Amount is mandatory")
    private String amount;

    @Length(min = 2, max = 20, message = "The length of description must be between 2 and 20 characters long")
    private String description;
}
