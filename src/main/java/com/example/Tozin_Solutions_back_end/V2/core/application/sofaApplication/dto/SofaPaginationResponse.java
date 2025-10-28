package com.example.Tozin_Solutions_back_end.V2.core.application.sofaApplication.dto;

import java.util.List;

public record SofaPaginationResponse(
        List<SofaOutput> content,
        int currentPage,
        int totalPages,
        long totalItems,
        int itemsPerPage
) {}
