<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.time.ZoneId" %>
<%!
    public Date convertToDate(LocalDateTime localDateTime) {
        return java.util.Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
%>
<!DOCTYPE html>
<html class="no-js" lang="en">

<!-- belle/home5-cosmetic.html   11 Nov 2019 12:25:38 GMT -->

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>Đơn hàng</title>
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
    <!--Body Content-->
    <div id="page-content">
        <!--Page Title-->
        <div class="page section-header text-center" style="margin-top: 5rem!important">
            <div class="page-title">
                <div class="wrapper">
                    <h1 class="page-width">Đơn hàng của tôi</h1>
                </div>
            </div>
        </div>
        <!--End Page Title-->

        <div class="container">
            <div class="row">
                <div class="col-12 col-sm-12 col-md-12 col-lg-12 main-col">
                    <form action="#">
                        <div class="wishlist-table table-content table-responsive">
                            <c:if test="${empty orders}">
                                <div class="text-center">Bạn chưa có đơn hàng nào<br>
                                    <a href="/product/list" class="btn--link cart-continue">
                                        <i class="icon icon-arrow-circle-left"></i> Tiếp tục mua sắm
                                    </a>
                                </div>

                            </c:if>
                            <c:if test="${not empty orders}">
                                <table class="table table-bordered">
                                    <thead>
                                    <tr>
                                        <th class="product-name text-center alt-font">Mã đơn hàng</th>
                                        <th class="product-price text-center alt-font">Ngày mua</th>
                                        <th class="product-price text-center alt-font">Địa chỉ nhận hàng</th>
                                        <th class="product-price text-center alt-font">Tổng tiền</th>
                                        <th class="product-price text-center alt-font">Trạng thái</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="o" items="${orders }">

                                        <fmt:parseDate value="${o.order.createDate}" pattern="yyyy-MM-dd'T'HH:mm:ss"
                                                       var="parsedDateTime" type="both"/>
                                        <fmt:formatDate pattern="dd-MM-yyyy HH:mm:ss" value="${ parsedDateTime }"
                                                        var="date"/>
                                        <tr>
                                            <td class="product-name text-center">
                                                <h4 class="no-margin">
                                                    <a title="Xem chi tiết" href="/order/detail/${o.order.id}"
                                                       style="text-decoration: none; font-size: 16px; font-weight: bold; color: #0a8dd3">
                                                        <span>${o.order.id}</span>
                                                    </a>
                                                </h4>
                                            </td>
                                            <td class="product-name text-center">
                                                <h4 class="no-margin">
                                                    <span>${date}</span>
                                                </h4>
                                            </td>
                                            <td class="product-name text-center">
                                                <h4 class="no-margin">
                                                    <span>${o.order.address}</span>
                                                </h4>
                                            </td>
                                            <td class="product-price text-center">
                                                <h4 class="no-margin">
                                                    <span class="amount"><fmt:formatNumber value="${o.order.total}" currencyCode="VND" /></span>
                                                </h4>
                                            </td>
                                            <td class="product-name text-center">
                                                <h4 class="no-margin">
                                                    <c:if test="${o.order.status == 1}">
                                                <span class="badge rounded-pill bg-warning"
                                                      style="font-size: 14px; border-radius: 4px;padding: 3px;">Chờ xác nhận</span>
                                                    </c:if>
                                                    <c:if test="${o.order.status == 2}">
                                                <span class="badge rounded-pill bg-success"
                                                      style="font-size: 14px; border-radius: 4px;padding: 3px;">Giao hàng</span>
                                                    </c:if>
                                                    <c:if test="${o.order.status == 0}">
                                                <span class="badge rounded-pill bg-danger"
                                                      style="font-size: 14px; border-radius: 4px;padding: 3px;">Đơn hàng bị hủy</span>
                                                    </c:if>
                                                </h4>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </c:if>

                        </div>
                    </form>
                </div>
            </div>
        </div>

    </div>
    <!--End Body Content-->
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