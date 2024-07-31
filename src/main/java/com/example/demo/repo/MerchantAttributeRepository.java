package com.example.demo.repo;

import com.example.demo.entity.MerchantAttribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchantAttributeRepository extends JpaRepository<MerchantAttribute, Long> {
    // Add custom query methods if needed
    MerchantAttribute findByMerchantIdAndMetaAttributeName(Long merchantId, String metaAttributeName);
}
