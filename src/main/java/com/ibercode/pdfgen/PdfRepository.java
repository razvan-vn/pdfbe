package com.ibercode.pdfgen;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PdfRepository extends MongoRepository<PdfFile, String> {
}
