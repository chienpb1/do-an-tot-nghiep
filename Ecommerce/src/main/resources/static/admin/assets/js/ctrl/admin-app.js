const app = angular.module("admin-app",["ngRoute"]);
app.config(function($routeProvider){
    $routeProvider
        .when("/category",{
            templateUrl:"/admin/assets/layout/category.html",
            controller:"category-ctrl"
        })
        .when("/brand",{
            templateUrl:"/admin/assets/layout/brand.html",
            controller:"brand-ctrl"
        })
        .when("/product",{
            templateUrl:"/admin/assets/layout/product.html",
            controller:"product-ctrl"
        })
        .when("/order",{
            templateUrl:"/admin/assets/layout/order.html",
            controller:"order-ctrl"
        })
        .when("/user",{
            templateUrl:"/admin/assets/layout/user.html",
            controller:"user-ctrl"
        })
        .when("/authenticate",{
            templateUrl:"/admin/assets/layout/authenticate.html",
            controller:"authenticate-ctrl"
        })
        .when("/log",{
            templateUrl:"/admin/assets/layout/log.html",
            controller:"log-ctrl"
        })
        .otherwise({
            templateUrl:"/admin/assets/layout/dashboard.html",
            controller:"dashboard-ctrl"
        })
});


