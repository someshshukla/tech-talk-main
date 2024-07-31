package com.example.demo.repo;
import com.example.demo.entity.MetaAttribute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MetaAttributeRepository extends JpaRepository<MetaAttribute, Long> {
    MetaAttribute findByMetaAttributeName(String metaAttributeName);
}
