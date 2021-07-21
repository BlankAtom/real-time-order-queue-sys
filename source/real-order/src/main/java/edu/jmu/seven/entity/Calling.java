package edu.jmu.seven.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Calling {

    private String o_id;
    private String c_id;
    private String c_phone;
    private LocalDateTime o_start_time;
    private long wait_time;

}
