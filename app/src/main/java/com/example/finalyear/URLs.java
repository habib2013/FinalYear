package com.example.finalyear;



public class URLs {

    private static final String ROOT_URL = "http://192.168.137.1/ams/Api.php?apicall=";
    private static final String NEW_ROOT_URL = "http://192.168.137.1/ams/v1/Api.php?apicall=";


    public static final String URL_REGISTER = ROOT_URL + "signup";
    public static final String URL_LOGIN= ROOT_URL + "login";

    public static final String URL_CREATE_COURSE = NEW_ROOT_URL + "createCourse";
    public static final String URL_CREATE_USER = NEW_ROOT_URL + "createUser";


    public static final String URL_READ_USER = NEW_ROOT_URL + "getUsers";
    public static final String URL_READ_LECTURER = NEW_ROOT_URL + "getLecturers";

    public static final String URL_DELETE_HERO = NEW_ROOT_URL + "deletehero&id=";

}