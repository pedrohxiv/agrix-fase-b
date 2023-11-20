package com.betrybe.agrix.services;

import com.betrybe.agrix.models.entities.Fertilizer;
import com.betrybe.agrix.models.repositories.FertilizerRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** Fertilizer service class. */
@Service
public class FertilizerService {
  /** Attributes. */
  private final FertilizerRepository fertilizerRepository;

  /** Constructor method. */
  @Autowired
  public FertilizerService(FertilizerRepository fertilizerRepository) {
    this.fertilizerRepository = fertilizerRepository;
  }

  /** Create fertilizer method. */
  public Fertilizer createFertilizer(Fertilizer fertilizer) {
    return fertilizerRepository.save(fertilizer);
  }

  /** Get all fertilizers method. */
  public List<Fertilizer> getAllFertilizers() {
    return fertilizerRepository.findAll();
  }
}
