package com.example.Tozin_Solutions_back_end.V2.core.application.logMovimentacaoApplication.dto;

import java.util.List;

public record LogMovimentacaoPaginationResponse(
        List<LogMovimentacaoOutput> content,
        int currentPage,
        int totalPages,
        long totalItems,
        int itemsPerPage
) {}
