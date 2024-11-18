package com.Ilker.Petify.service;

import com.Ilker.Petify.entity.ReHoming;
import com.Ilker.Petify.request.announcement.CreateAnnouncementRequest;
import com.Ilker.Petify.request.announcement.UpdateAnnouncementRequest;

import java.util.List;

public interface ReHomingService {
    List<ReHoming> getAll();
    ReHoming add(CreateAnnouncementRequest request);
    ReHoming update(UpdateAnnouncementRequest request, Long id);
    void delete(Long id);
}
