package com.example.demo.controller;

import com.example.demo.entity.MetaAttribute;
import com.example.demo.service.MetaAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/meta-attributes")
public class MetaAttributeController {

    @Autowired
    private MetaAttributeService metaAttributeService;

    @PostMapping
    public MetaAttribute createMetaAttribute(@RequestBody MetaAttribute metaAttribute) {
        return metaAttributeService.createMetaAttribute(metaAttribute);
    }

    @PutMapping("/{id}")
    public MetaAttribute updateMetaAttribute(@PathVariable Long id, @RequestBody MetaAttribute metaAttribute) {
        return metaAttributeService.updateMetaAttribute(id, metaAttribute);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMetaAttribute(@PathVariable Long id) {
        metaAttributeService.deleteMetaAttribute(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public MetaAttribute getMetaAttribute(@PathVariable Long id) {
        return metaAttributeService.getMetaAttribute(id);
    }

    @GetMapping("/by-name/{metaAttributeName}")
    public MetaAttribute getMetaAttributeByName(@PathVariable String metaAttributeName) {
        return metaAttributeService.getMetaAttributeByName(metaAttributeName);
    }

    @GetMapping("/")
    public List<MetaAttribute> getMetaAttribute() {
        return metaAttributeService.getAllMetaAttribute();
    }
}

