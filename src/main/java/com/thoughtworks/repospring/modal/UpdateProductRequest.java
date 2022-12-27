package com.thoughtworks.repospring.modal;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductRequest {
    private String weight;
    private String amount;
    private String description;

}
