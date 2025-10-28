package com.example.Tozin_Solutions_back_end.V2.core.application.pecaApplication.dto;

public record PecaPaginationRequest(
        int page,
        int size,
        String sortBy,
        String sortDirection,
        String filter
) {
    public PecaPaginationRequest {
        if (page < 1) page = 1;
        if (size < 1) size = 10;
        if (sortBy == null || sortBy.isBlank()) sortBy = "nome";
        if (sortDirection == null || sortDirection.isBlank()) sortDirection = "asc";
        if (filter == null) filter = "";
    }

    public static PecaPaginationRequest defaultRequest() {
        return new PecaPaginationRequest(1, 10, "nome", "asc", "");
    }
}
