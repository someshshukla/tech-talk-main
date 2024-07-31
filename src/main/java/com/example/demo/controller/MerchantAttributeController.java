package com.example.demo.controller;

import com.example.demo.entity.MerchantAttribute;
import com.example.demo.entity.MetaAttribute;
import com.example.demo.service.MerchantAttributeService;
import com.example.demo.service.MetaAttributeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/merchants")
public class MerchantAttributeController {

    @Autowired
    private MerchantAttributeService merchantAttributeService;

    @Autowired
    private MetaAttributeService metaAttributeService;

    @GetMapping("/{merchantId}/{metaAttributeName}")
    public ResponseEntity<?> getMerchantAttributeByAttributeName(@PathVariable Long merchantId, @PathVariable String metaAttributeName) {
        Object merchantAttribute = merchantAttributeService.getMerchantAttributeByMetaAttributeName(merchantId,metaAttributeName);
        if (merchantAttribute == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(merchantAttribute);
    }

    @PostMapping("/{merchantId}/{metaAttributeName}")
    public ResponseEntity<?> saveAttribute(@PathVariable Long merchantId, @PathVariable String metaAttributeName, @RequestBody String jsonData) {
        boolean saved = merchantAttributeService.saveMerchantAttribute(merchantId, metaAttributeName, jsonData);
        if (saved) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().body("Invalid JSON data or MetaAttribute not found");
        }
    }
}
