package com.example.Tozin_Solutions_back_end.V2.core.application.sofaApplication.dto;

public record SofaPaginationRequest(
        int page,
        int size,
        String sortBy,
        String sortDirection,
        String filter
) {
    public SofaPaginationRequest {
        if (page < 1) page = 1;
        if (size < 1) size = 8;
        if (sortBy == null || sortBy.isBlank()) sortBy = "modelo";
        if (sortDirection == null || sortDirection.isBlank()) sortDirection = "asc";
        if (filter == null) filter = "";
    }
    public static SofaPaginationRequest defaultRequest() {
        return new SofaPaginationRequest(1, 8, "modelo", "asc", "");
    }
}
