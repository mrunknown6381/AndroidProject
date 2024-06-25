package com.example.travelbuddy.navigation

sealed class routes(val routes:String){
    object Screenonboarding : routes(routes = "onboarding")
    object Screenwelcome : routes(routes = "welcome")
    object Screenlogin : routes(routes = "login")
    object Screenregister : routes(routes = "register")
    object Screentac : routes(routes = "terms")
    object Screenfp : routes(routes = "forget")
    object homescreen : routes(routes = "home")
    object searchscreen : routes(routes = "search")
    object planscreen : routes(routes = "plan")
    object wishlistscreen : routes(routes = "wishlist")
    object profilescreen : routes(routes = "profile")
    object bottombar : routes(routes = "bottom_nav")
    object editprofile : routes(routes = "edit_profile")
    object catselection : routes(routes = "category_selection")
    object privacypolicy : routes(routes = "privacy_policy")
    object loading : routes(routes = "load_screen")
    object details:routes(routes = "placeDetails")
    object catPlaces:routes(routes = "catPlace")
    object detailsCity:routes(routes = "detailsCity")
    object hotelDetails:routes(routes = "detailsHotel")
    object restoDetails:routes(routes = "detailsResto")
    object locreq:routes(routes = "location_request")
    object initCat:routes(routes = "category_request")
    object exPlans:routes(routes = "explore_plans")
    object planDetails:routes(routes = "planDetails")


}