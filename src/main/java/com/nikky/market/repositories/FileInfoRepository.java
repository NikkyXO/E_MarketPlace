package com.nikky.market.repositories;

import com.nikky.market.entities.files.FileInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileInfoRepository extends JpaRepository<FileInfo, Long> {
}
