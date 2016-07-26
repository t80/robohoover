package com.yoti.robohoover.repositories;

import com.yoti.robohoover.domain.RobohooverServiceRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RobohooverRequestRepository extends JpaRepository<RobohooverServiceRequest, Long> {
}
