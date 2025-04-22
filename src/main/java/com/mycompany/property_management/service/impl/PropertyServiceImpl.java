package com.mycompany.property_management.service.impl;

import com.mycompany.property_management.converter.PopertyConverter;
import com.mycompany.property_management.dto.PropertyDTO;
import com.mycompany.property_management.entity.PropertyEntity;
import com.mycompany.property_management.repository.PropertyRepository;
import com.mycompany.property_management.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private PopertyConverter converter;

    @Override
    public PropertyDTO saveProperty(PropertyDTO propertyDTO) {

        PropertyEntity pe = converter.convertDTOtoEntity(propertyDTO);
        pe = propertyRepository.save(pe);
        propertyDTO = converter.convertEntitytoDTO(pe);
        return propertyDTO;
    }

    public List<PropertyDTO> getAllProperties() {

        List<PropertyEntity> listoF_props = (List<PropertyEntity>) propertyRepository.findAll();
        List<PropertyDTO> propsLIst = new ArrayList<PropertyDTO>();
        for (PropertyEntity pe : listoF_props) {
            PropertyDTO dt0 = converter.convertEntitytoDTO(pe);
            propsLIst.add(dt0);
        }
        return propsLIst;
    }

    @Override
    public PropertyDTO updateProperty(PropertyDTO propertyDTO,Long propertyId) {
        Optional<PropertyEntity> optEn = propertyRepository.findById(propertyId);
        PropertyDTO dto = null;

        if (optEn.isPresent()) {
            PropertyEntity pe = optEn.get();
            pe.setTitle(propertyDTO.getTitle());
            pe.setDescription(propertyDTO.getDescription());
            pe.setPrice(propertyDTO.getPrice());
            pe.setAddress(propertyDTO.getAddress());
            dto = converter.convertEntitytoDTO(pe);  // in realta abbbiamo gia nei pametri l'oggetto, ma possiameo comunque convertire l'entity
            propertyRepository.save(pe);
        }

        return dto;
    }

    @Override
    public PropertyDTO updatePropertyDescription(PropertyDTO propertyDTO, Long propertyId) {
        Optional<PropertyEntity> optEn = propertyRepository.findById(propertyId);
        PropertyDTO dto = null;

        if (optEn.isPresent()) {
            PropertyEntity pe = optEn.get();
            pe.setDescription(propertyDTO.getDescription());
            dto = converter.convertEntitytoDTO(pe);  // in realta abbbiamo gia nei pametri l'oggetto, ma possiameo comunque convertire l'entity
            propertyRepository.save(pe);
        }
        return dto;
    }

    @Override
    public PropertyDTO updatePropertyPrice(PropertyDTO propertyDTO, Long propertyId) {
        Optional<PropertyEntity> optEn = propertyRepository.findById(propertyId);
        PropertyDTO dto = null;

        if (optEn.isPresent()) {
            PropertyEntity pe = optEn.get();
            pe.setPrice(propertyDTO.getPrice());
            dto = converter.convertEntitytoDTO(pe);  // in realta abbbiamo gia nei pametri l'oggetto, ma possiameo comunque convertire l'entity
            propertyRepository.save(pe);
        }
        return dto;
    }

    @Override
    public void deleteProperty(Long propertyId) {
        propertyRepository.deleteById(propertyId);
    }
}
