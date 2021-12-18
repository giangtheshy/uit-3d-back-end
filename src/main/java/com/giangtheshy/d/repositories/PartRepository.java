package com.giangtheshy.d.repositories;

import com.giangtheshy.d.models.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PartRepository extends JpaRepository<Part,Long> {
    @Modifying
    @Query(value = "UPDATE part SET coordinate_id=?2 WHERE id=?1",nativeQuery = true)
    void updateId(Long pid,Long cid);
}
