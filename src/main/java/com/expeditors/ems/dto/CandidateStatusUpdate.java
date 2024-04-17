package com.expeditors.ems.dto;

import com.expeditors.ems.entity.Candidate;
import lombok.Data;

@Data
public class CandidateStatusUpdate {
    private Integer id;//candidate id
    private Integer status;
}
