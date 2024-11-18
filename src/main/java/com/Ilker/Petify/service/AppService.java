package com.Ilker.Petify.service;

public interface AppService {

    void isAdminExistsByEmail(String email);
    void checkAdminExistsByEmail(String email);
    void checkPetSitterExistsByEmail(String email);
    void isPetSitterExistsByEmail(String email);
    void isCustomerExistsByEmail(String email);
    void checkCustomerExistsByEmail(String email);
    void checkCorporateCustomerExistsByEmail(String email);
    void isCorporateCustomerExistsByEmail(String email);
}
