package com.yoti.robohoover.repositories;

import com.yoti.robohoover.domain.RobohooverServiceResponse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RobohooverResponseRepository extends JpaRepository<RobohooverServiceResponse, Long> {
}
