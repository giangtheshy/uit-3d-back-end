package com.giangtheshy.d.repositories;

import com.giangtheshy.d.models.Element;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElementRepository extends JpaRepository<Element,Long> {
}
