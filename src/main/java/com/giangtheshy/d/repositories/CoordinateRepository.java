package com.giangtheshy.d.repositories;

import com.giangtheshy.d.models.Coordinate;
import com.giangtheshy.d.models.Enums.TypeCoordinate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CoordinateRepository extends JpaRepository<Coordinate,Long> {
    @Modifying
    @Query(value = "UPDATE coordinate SET element_id=?2 WHERE id=?1",nativeQuery = true)
    void updateId(Long cid,Long eid);
}
