<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html class="no-js" lang="en">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>Từ chối truy cập</title>
    <meta name="description" content="description">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Favicon -->
    <link rel="shortcut icon" href="/assets/images/favicon.png" />
    <!-- Plugins CSS -->
    <link rel="stylesheet" href="/assets/css/plugins.css">
    <!-- Bootstap CSS -->
    <link rel="stylesheet" href="/assets/css/bootstrap.min.css">
    <!-- Main Style CSS -->
    <link rel="stylesheet" href="/assets/css/style.css">
    <link rel="stylesheet" href="/assets/css/custom.css">
    <link rel="stylesheet" href="/assets/css/responsive.css">
    <!-- angularjs -->
    <script
            src="https://ajax.googleapis.com/ajax/libs/angularjs/1.8.2/angular.min.js"></script>
    <script
            src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular-route.js"></script>
</head>

<body ng-app="shopping-app" ng-controller="shopping-ctrl"
      class="template-index belle home5-cosmetic">
<div id="pre-loader">
    <img src="/assets/images/loader.gif" alt="Loading..." />
</div>
<div class="pageWrapper">
    <%@include file="./layout/_header.jsp"%>

    <!--Body Content-->
    <div id="page-content">
        <!--Page Title-->
        <div class="page section-header text-center" style="margin-top: 5rem!important">
            <div class="page-title">
                <div class="wrapper">
                    <h1 style="color: red" class="page-width">Bạn không có quyền truy cập vào trang này</h1>
                    <a href="/" class="btn btn-primary mb-2">Quay lại trang chủ</a>
                </div>
            </div>
        </div>
    </div>
    <!--End Body Content-->

    <!--Footer-->
    <%@include file="./layout/_footer.jsp"%>
    <!--End Footer-->

    <!--Scoll Top-->
    <span id="site-scroll"><i class="icon anm anm-angle-up-r"></i></span>
    <!--End Scoll Top-->

    <!-- Including Jquery -->
    <script src="/assets/js/vendor/jquery-3.3.1.min.js"></script>
    <script src="/assets/js/vendor/modernizr-3.6.0.min.js"></script>
    <script src="/assets/js/vendor/jquery.cookie.js"></script>
    <script src="/assets/js/vendor/wow.min.js"></script>
    <!-- Incing Jascript -->
    <script src="/assets/js/bootstrap.min.js"></script>
    <script src="/assets/js/plugins.js"></script>
    <script src="/assets/js/popper.min.js"></script>
    <script src="/assets/js/lazysizes.js"></script>
    <script src="/assets/js/main.js"></script>
    <!-- Shopping cart -->
    <script src="/assets/js/shopping-cart.js"></script>
    <!--For Newsletter Popup-->
    <script>
        jQuery(document).mouseup(
            function(e) {
                var container = jQuery('#popup-container');
                if (!container.is(e.target)
                    && container.has(e.target).length === 0) {
                    container.fadeOut();
                    jQuery('#modalOverly').fadeIn(200);
                    jQuery('#modalOverly').hide();
                }
            });
    </script>
    <!--End For Newsletter Popup-->
</div>
</body>


</html>