package fr.m2i.dao;

import fr.m2i.model.Company;

public interface CompanyDao {
    Company findByIdFetchProducts(Long id);
}
