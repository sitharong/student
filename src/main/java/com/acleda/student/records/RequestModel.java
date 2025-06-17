package com.acleda.student.records;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

public final class RequestModel {
    @Data
    public static class StudentNew {
        Long id;
        String name;
    }

    @Data
    public static class StudentUpdate {
        @NotBlank(message = "Name cannot be blank")
        Long id;
        String name;
    };
}
