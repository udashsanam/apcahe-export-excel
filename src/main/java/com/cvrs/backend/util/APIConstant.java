package com.cvrs.backend.util;

public interface APIConstant {
    static final String API = "/api/cvrs";

    static final String ADMIN = API + "/admin";
    static final String AGE_CATEGORY = API + "/ageCategory";
    static final String CITIZEN = API + "/citizen";
    static final String VACCINE = API + "/vaccine";
    static final String LOCATION = API + "/location";
    static final String MANUFACTURING_COMPANY = API + "/manufacturing_company";
    static final String MEDICAL_CONDITION = API + "/medical_condition";
    static final String OCCUPATION = API + "/occupation";
    static final String VACCINE_DISTRIBUTION_CENTER = API + "/vaccine_distribution_center";
    static final String VACCINE_DISTRIBUTION_CENTER_TO_VACCINE = API + "/vaccine_distribution_center_to_vaccine";

    /************* Common Controller **************/
    static final String FIND_ALL = "/findAll";
    static final String FIND_BY_ID = "/find/{id}";
    static final String DELETE_BY_ID = "/delete/{id}";
    static final String EDIT = "/edit";
    static final String UPDATE = "/update";

    /************ Citizen Controller **********/
    static final String FIND_BY_STATUS_CODE = "/statusCode";
    static final String FIND_BY_AGE_CATEGORIES = "/ageCategories";
    static final String GET_VACCINE_REPORT = "/vaccinationReport/{registrationNumber}";


    /*********** Vaccine Controller ***********/
    static final String FIND_ALL_WITH_DISTRIBUTION_CENTER = "/distributionCenter";

}
