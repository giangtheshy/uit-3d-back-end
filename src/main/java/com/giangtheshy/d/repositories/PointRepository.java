package com.giangtheshy.d.repositories;

import com.giangtheshy.d.models.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PointRepository extends JpaRepository<Point,Long> {
    @Modifying
    @Query(value = "UPDATE point SET part_id=?2 WHERE id=?1",nativeQuery = true)
    void updateId(Long pointId,Long pid);
}
