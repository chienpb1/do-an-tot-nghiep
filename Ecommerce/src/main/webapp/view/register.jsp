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
    <title>Đăng ký tài khoản</title>
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
    <%@include file="./layout/_header.jsp" %>

    <!--Body Content-->
    <div class="mt-5" id="page-content">
        <!--Page Title-->
        <div class="page section-header text-center" style="margin-top: 5rem!important">
            <div class="page-title">
                <div class="wrapper">
                    <h1 class="page-width">Đăng ký tài khoản</h1>
                </div>
            </div>
        </div>
        <!--End Page Title-->

        <div class="container">
            <div class="row">
                <div class="col-12 col-sm-12 col-md-6 col-lg-6 main-col offset-md-3">
                    <div class="mb-4">
                        <form:form modelAttribute="account" name="frmregister" method="post" ng-submit="create()"
                                   id="CustomerLoginForm" accept-charset="UTF-8"
                                   class="contact-form">
                            <div class="row">
                                <c:if test="${not empty error }">
                                    <div class="alert alert-danger alert-dismissible fade show ml-3" role="alert">
                                        <strong>${error} </strong>
                                        <button type="button" class="close" data-dismiss="alert"
                                                aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                </c:if>
                            </div>
                            <div class="row">
                                <div class="col-12 col-sm-12 col-md-12 col-lg-12">
                                    <div class="form-group">
                                        <label for="FirstName">Tên đăng nhập
                                            <span style="color: red">*</span>
                                        </label>
                                        <form:input path="username" ng-model="username" name="username"
                                                    ng-required="true"
                                                    placeholder="Nhập tên đăng nhập"
                                                    id="FirstName" autofocus="" ng-pattern="/^[a-zA-Z0-9._\\-]*$/"/>
                                        <div ng-show="frmregister.username.$dirty && frmregister.username.$error.required"
                                             class="badge badge-danger m-2">Vui lòng nhập tên đăng nhập
                                        </div>
                                        <div ng-show="frmregister.username.$dirty && frmregister.username.$error.pattern"
                                             class="badge badge-danger m-2">Tên đăng nhập không được chứa kí tự đặc biệt
                                        </div>
                                    </div>
                                </div>
                                <div class="col-12 col-sm-12 col-md-12 col-lg-12">
                                    <div class="form-group">
                                        <label for="FirstName">Họ và tên<span
                                                style="color: red">*</span></label>
                                        <form:input path="fullname" ng-model="fullname" name="fullname"
                                                    ng-required="true"
                                                    placeholder="Nhập họ và tên"
                                                    id="FirstName" autofocus=""/>
                                        <div ng-show="frmregister.fullname.$dirty && frmregister.fullname.$error.required"
                                             class="badge badge-danger m-2">Vui lòng nhập họ và tên
                                        </div>
                                    </div>
                                </div>
                                <div class="col-12 col-sm-12 col-md-12 col-lg-12">
                                    <div class="form-group">
                                        <label for="FirstName">Số điện thoại<span
                                                style="color: red">*</span></label>
                                        <form:input path="phoneNumber" ng-model="phoneNumber" name="phoneNumber"
                                                    ng-required="true" placeholder="Nhập số điện thoại"
                                                    id="FirstName" autofocus="" maxlength="10"
                                                    ng-pattern="/^(0?)([1-9][2-9]|5[6|8|9]|7[0|6-9]|8[0-6|8|9]|9[0-4|6-9])[0-9]{7}$/"/>
                                        <div ng-show="frmregister.phoneNumber.$dirty && frmregister.phoneNumber.$error.required"
                                             class="badge badge-danger m-2">Vui lòng nhập số điện thoại
                                        </div>
                                        <div ng-show="frmregister.phoneNumber.$dirty && frmregister.phoneNumber.$error.pattern"
                                             class="badge badge-danger m-2">Số điện thoại không đúng định dạng
                                        </div>
                                    </div>
                                </div>
                                <div class="col-12 col-sm-12 col-md-12 col-lg-12">
                                    <div class="form-group">
                                        <label for="CustomerEmail">Email<span
                                                style="color: red">*</span></label>
                                        <form:input type="email" path="email" ng-model="email" name="email"
                                                    ng-required="true" placeholder="Địa chỉ email"
                                                    id="CustomerEmail" class="" autocorrect="off"
                                                    autocapitalize="off" autofocus=""/>
                                        <div ng-show="frmregister.email.$dirty && frmregister.email.$error.required"
                                             class="badge badge-danger m-2">Vui lòng nhập email
                                        </div>
                                        <div ng-show="frmregister.email.$dirty && frmregister.email.$error.email"
                                             class="badge badge-danger m-2">Email không đúng định dạng
                                        </div>
                                    </div>
                                </div>
                                <div class="col-12 col-sm-12 col-md-12 col-lg-12">
                                    <div class="form-group">
                                        <label for="CustomerPassword">Mật khẩu<span
                                                style="color: red">*</span></label>
                                        <form:password path="password" ng-model="password" value="" ng-required="true"
                                                       name="password" placeholder="Nhập mật khẩu"
                                                       ng-pattern="/^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/"
                                                       id="CustomerPassword" class=""/>
                                        <div ng-show="frmregister.password.$dirty && frmregister.password.$error.required"
                                             class="badge badge-danger m-2">Vui lòng nhập mật khẩu
                                        </div>
                                        <div ng-show="frmregister.password.$dirty && frmregister.password.$error.pattern"
                                             class="badge badge-danger m-2">Mật khẩu phải tối thiểu 8 ký tự bao gồm
                                            chữ
                                            in hoa, chữ in thường, ký tự đặc biệt và số.
                                        </div>
                                    </div>
                                </div>

                                <div class="col-12 col-sm-12 col-md-12 col-lg-12">
                                    <div class="form-group">
                                        <label for="CustomerPassword1">Xác nhận mật khẩu<span
                                                style="color: red">*</span></label>
                                        <form:password path="" ng-model="password1" value=""
                                                       name="password1" ng-required="true"
                                                       placeholder="Xác nhận mật khẩu"
                                                       id="CustomerPassword1" class=""/>
                                        <div ng-show="frmregister.password1.$dirty && frmregister.password1.$error.required"
                                             class="badge badge-danger m-2">Vui lòng nhập xác nhận
                                            mật khẩu
                                        </div>
                                        <div ng-show="frmregister.password1.$dirty && password != password1"
                                             class="badge badge-danger m-2">Xác nhận mật khẩu không
                                            đúng
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="text-center col-12 col-sm-12 col-md-12 col-lg-12">
                                    <button ng-disabled="frmregister.$invalid" type="submit"
                                            class="btn mb-3">Tạo tài khoản
                                    </button>
                                </div>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>
<!--End Body Content-->

<!--Footer-->
<%@include file="./layout/_footer.jsp" %>
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