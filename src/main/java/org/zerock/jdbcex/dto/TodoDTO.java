package org.zerock.jdbcex.dto;

import lombok.*;

import java.time.LocalDate;

@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TodoDTO {

    private Long tno;

    private String title;

    private LocalDate dueDate;

    private boolean finished;
}
