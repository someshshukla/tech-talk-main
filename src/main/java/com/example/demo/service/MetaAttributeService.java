package com.example.demo.service;

import com.example.demo.entity.MetaAttribute;
import com.example.demo.repo.MetaAttributeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MetaAttributeService {

    @Autowired
    private MetaAttributeRepository metaAttributeRepository;

    public MetaAttribute createMetaAttribute(MetaAttribute metaAttribute) {
        String currentTime = LocalDateTime.now().toString();
        metaAttribute.setCreatedAt(currentTime);
        metaAttribute.setUpdatedAt(currentTime);

        return metaAttributeRepository.save(metaAttribute);
    }

    public MetaAttribute updateMetaAttribute(Long id, MetaAttribute metaAttribute) {
        if (!metaAttributeRepository.existsById(id)) {
            System.out.println("Cannot find the metaAttribute "+ id);
        }
        metaAttribute.setId(id);
        return metaAttributeRepository.save(metaAttribute);
    }

    public void deleteMetaAttribute(Long id) {
        metaAttributeRepository.deleteById(id);
    }

    public MetaAttribute getMetaAttribute(Long id) {
        return metaAttributeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("MetaAttribute not found"));
    }

    public MetaAttribute getMetaAttributeByName(String metaAttributeName) {
        return metaAttributeRepository.findByMetaAttributeName(metaAttributeName);
    }

    public List<MetaAttribute> getAllMetaAttribute(){
        return metaAttributeRepository.findAll();
    }
}
