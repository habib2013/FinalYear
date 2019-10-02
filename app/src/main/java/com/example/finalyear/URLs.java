package com.example.finalyear;



public class URLs {

    private static final String ROOT_URL = "http://192.168.137.1/ams/Api.php?apicall=";
    private static final String NEW_ROOT_URL = "http://192.168.137.1/ams/v1/Api.php?apicall=";

    public static final String URL_REGISTER = ROOT_URL + "signup";
    public static final String URL_LOGIN= ROOT_URL + "login";


    public static final String URL_CREATE_HERO = NEW_ROOT_URL + "createCourse";
    public static final String URL_READ_HEROES = NEW_ROOT_URL + "getheroes";
    public static final String URL_UPDATE_HERO = NEW_ROOT_URL + "updatehero";
    public static final String URL_DELETE_HERO = NEW_ROOT_URL + "deletehero&id=";

}