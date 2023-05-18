<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html class="no-js" lang="en">

<!-- belle/home5-cosmetic.html   11 Nov 2019 12:25:38 GMT -->

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>Thông tin tài khoản</title>
    <meta name="description" content="description">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Favicon -->
    <link rel="shortcut icon" href="/assets/images/favicon.png"/>
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
    <img src="/assets/images/loader.gif" alt="Loading..."/>
</div>
<div class="pageWrapper">
    <%@include file="../layout/_header.jsp" %>

    <!--Body Content-->
    <div class="mt-5" id="page-content">
        <!--Page Title-->
        <div class="page section-header text-center" style="margin-top: 5rem!important">
            <div class="page-title">
                <div class="wrapper">
                    <h1 class="page-width">Thông tin tài khoản</h1>
                </div>
            </div>
        </div>
        <!--End Page Title-->

        <div class="container">
            <div class="row">
                <div class="col-12 col-sm-12 col-md-6 col-lg-6 main-col offset-md-3">
                    <div class="mb-4">
                        <form name="frmEditAccount">
                            <fieldset>
                                <div class="row">
                                    <div class="form-group col-12 required">
                                        <label for="frmEditAccount-username">Tên đăng nhập<span
                                                style="color: red">*</span></label>
                                        <input style="background-color: #EEEEEE" name="username"
                                               ng-model="userDetail.username"
                                               id="frmEditAccount-username" readonly="readonly" type="text">
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="form-group col-12 required">
                                        <label for="frmEditAccount-fullname">Họ và tên<span
                                                style="color: red">*</span></label>
                                        <input name="fullname" ng-model="userDetail.fullname"
                                               id="frmEditAccount-fullname"  type="text">
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="form-group col-12 required">
                                        <label for="frmEditAccount-phoneNumber">Số điện thoại<span
                                                style="color: red">*</span></label>
                                        <input name="phoneNumber" ng-model="userDetail.phoneNumber"
                                               id="frmEditAccount-phoneNumber" type="text">
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="form-group col-12 required">
                                        <label for="frmEditAccount-email">E-Mail <span
                                                style="color: red">*</span></label>
                                        <input name="email" ng-model="userDetail.email"
                                               id="frmEditAccount-email"  type="email">
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="form-group col-12 required">
                                        <label for="frmEditAccount-password">Mật khẩu <span
                                                style="color: red">*</span></label>
                                        <input name="password" ng-model="userDetail.password"
                                               id="frmEditAccount-password" type="password">
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="text-center col-12 col-sm-12 col-md-12 col-lg-12">
                                        <button type="submit" ng-click="editMyAccount()"
                                                class="btn mb-3">Cập nhật
                                        </button>
                                    </div>
                                </div>
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>
<!--End Body Content-->

<!--Footer-->
<%@include file="../layout/_footer.jsp" %>
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
        function (e) {
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