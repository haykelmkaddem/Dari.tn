package com.esprit.dari.services.furnituresServices;

import com.esprit.dari.entities.furnituresEntities.Furniture;
import com.esprit.dari.entities.furnituresEntities.Transportor;

import java.util.List;

public interface ITransporterService {

    public Long ajouterTransportor(Transportor transportor);
    public void deleteTransportorById(Long transportorId);
    public List<Transportor> getAllTransportor();
    public void affecterTransporterToCommand(Long transportorId, Long commandId);
    public void changeCommandEtatAndTransporter(Long commandId , Long transporterId);
    public Long modifiererTransportor(Long id, Transportor transportor);
    public Transportor getTransportorById(Long transportorId);
}
