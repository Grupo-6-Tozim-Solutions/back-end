package com.example.Tozin_Solutions_back_end.V2.core.application.pecaApplication.dto;

import java.util.List;

public record PecaPaginationResponse(
        List<PecaOutput> content,
        int currentPage,
        int totalPages,
        long totalItems,
        int itemsPerPage
) {}
