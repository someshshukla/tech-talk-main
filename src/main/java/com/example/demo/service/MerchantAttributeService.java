package com.example.demo.service;

import com.example.demo.entity.MerchantAttribute;
import com.example.demo.entity.MetaAttribute;
import com.example.demo.repo.MerchantAttributeRepository;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.ValidationMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class MerchantAttributeService {

    @Autowired
    private MerchantAttributeRepository merchantAttributeRepository;

    @Autowired
    private MetaAttributeService metaAttributeService;

    private final JsonSchemaFactory schemaFactory = JsonSchemaFactory.getInstance();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public boolean saveMerchantAttribute(Long merchantId, String metaAttributeName, String jsonData) {
        MetaAttribute metaAttribute = metaAttributeService.getMetaAttributeByName(metaAttributeName);
        if (metaAttribute == null) {
            return false;
        }

        // Validate JSON data against meta-attribute schema
        boolean isValid = validateJsonData(jsonData, metaAttribute.getSchemaJson());
        if (!isValid) {
            return false;
        }

        // Save attribute value
        MerchantAttribute merchantAttribute = new MerchantAttribute();
        merchantAttribute.setMerchantId(merchantId);
        merchantAttribute.setMetaAttributeId(metaAttribute.getId());
        merchantAttribute.setMetaAttributeName(metaAttributeName);
        merchantAttribute.setJsonData(jsonData);
        merchantAttributeRepository.save(merchantAttribute);

        return true;
    }

    public Object getMerchantAttributeByMetaAttributeName(Long merchantId, String metaAttributeName)  {
        MerchantAttribute merchantAttribute =merchantAttributeRepository.findByMerchantIdAndMetaAttributeName(merchantId,metaAttributeName);
        try {
            return objectMapper.readTree(merchantAttribute.getJsonData());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    private boolean validateJsonData(String jsonData, String schemaJson) {
        try {
            // Parse JSON schema
            JsonNode schemaNode = objectMapper.readTree(schemaJson);
            JsonSchema schema = schemaFactory.getSchema(schemaNode);

            // Parse JSON data
            JsonNode dataNode = objectMapper.readTree(jsonData);

            // Validate data against schema
            Set<ValidationMessage> validationMessages = schema.validate(dataNode);

            // If there are no validation errors, return true
            return ((Set<?>) validationMessages).isEmpty();
        } catch (Exception e) {
            e.printStackTrace(); // Handle exception appropriately
            return false;
        }
    }
}

