<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html class="no-js" lang="en">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>Phụ kiện điện tử</title>
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
    <div id="page-content">
        <!--Home slider-->
        <%@include file="../layout/_slide.jsp" %>
        <!--End Home slider-->

        <!--Collection Tab slider-->

        <!--Collection Tab slider-->

        <div class="container">
            <div class="row">
                <div class="col-12 col-sm-12 col-md-12 col-lg-12">
                    <div class="section-header text-center">
                        <h2 class="h2">Thương hiệu</h2>
                    </div>
                </div>
            </div>
        </div>
        <!--Logo Slider-->
        <div class="section logo-section">
            <div class="container">
                <div class="row">
                    <div class="col-12 col-sm-12 col-md-12 col-lg-12">
                        <div class="logo-bar">
                            <c:forEach var="brand" items="${brands}">
                                <div class="logo-bar__item">
                                    <a href="/product/list?bid=${brand.id}"><img
                                            src="/images/brand/${brand.image}" alt="${brand.name}"
                                            title="${brand.name}"/></a>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!--Featured Product-->
        <div class="product-rows section">
            <div class="container">
                <div class="row">
                    <div class="col-12 col-sm-12 col-md-12 col-lg-12">
                        <div class="section-header text-center">
                            <h2 class="h2">Sản phẩm mới</h2>
                        </div>
                    </div>
                </div>
                <div class="grid-products">
                    <div class="row">
                        <c:forEach var="d" items="${db}" end="14">
                            <div
                                    class="col-6 col-sm-6 col-md-4 col-lg-4 item grid-view-item style2">
                                <div class="grid-view_image">

                                    <!-- start product image -->
                                    <a href="/product/detail/${d.product.id}"
                                       class="grid-view-item__link"> <!-- image --> <img
                                            class="product grid-view-item__image primary blur-up lazyload "
                                            src="/images/product/${d.images[0]}" alt="${d.product.name}"
                                            title="${d.product.name}"> <!-- End image --> <!-- Hover image -->
                                        <img
                                                class="product grid-view-item__image hover blur-up lazyload"
                                                data-src="/images/product/${d.images[1]}"
                                                src="/images/product/${d.images[1]}" alt="${d.product.name}"
                                                title="${d.product.name}"> <!-- End hover image  -->
                                        <!-- product label -->
                                    </a>
                                    <!-- End product label -->
                                    <!-- end product image -->

                                    <!--start product details -->
                                    <div class="product-details hoverDetails text-center mobile">
                                        <!-- product name -->
                                        <div class="product-name">
                                            <a href="/product/detail/${d.product.id}">${d.product.name}</a>
                                        </div>
                                        <!-- End product name -->

                                        <!-- product price -->
                                        <div class="product-price">
                                            <span class="price"><fmt:formatNumber value="${d.product.price}" type="currency" currencyCode="VND" /></span>
                                        </div>
                                        <!-- End product price -->

                                        <!-- product button -->
                                        <div class="button-set">
                                            <!-- Start product button -->
                                            <c:if test="${empty sessionScope.userAdmin}">
                                                <c:set var="qty" value="${d.product.unitsInStock}"/>
                                                <!-- lấy giá trị của product.product.price và gán vào biến price -->
                                                <c:if test="${qty > 0}">
                                                    <form class="variants add" action="#" method="post">
                                                        <button ng-click="cart.add(${d.product.id})"
                                                                class="btn cartIcon btn-addto-cart" type="button"
                                                                tabindex="0">
                                                            <i class="icon anm anm-bag-l"></i>
                                                        </button>
                                                    </form>
                                                </c:if>

                                                <c:if test="${qty <= 0}">
                                                    <button style="background-color: red; color: white" class="btn btn-addto-cart" type="button">Hết hàng
                                                    </button>
                                                </c:if>

                                            </c:if>

                                        </div>
                                        <!-- end product button -->
                                    </div>
                                    <!-- Variant -->
                                    <ul class="swatches text-center">
                                        <c:forEach var="i" items="${d.images}">
                                            <li class="swatch medium rounded"><img
                                                    src="/images/product/${i}" alt="image"/></li>
                                        </c:forEach>
                                    </ul>
                                    <!-- End Variant -->
                                    <!-- End product details -->
                                </div>
                            </div>
                        </c:forEach>


                    </div>
                </div>
                <div class="col-12 col-sm-12 col-md-12 col-lg-12">
                    <div class="section-header text-center">
                        <a href="/product/list?kw=" class="btn"
                           style="vertical-align: middle; text-align: center;">TẤT CẢ
                            SẢN PHẨM</a>
                    </div>
                </div>
            </div>
        </div>
        <!--End Featured Product-->

        <!--Parallax Section-->
        <div class="section">
            <div class="hero hero--large hero__overlay bg-size">
                <img class="bg-img blur-up"
                     src="/assets/images/parallax-banners/tomtoc.jpg" alt=""/>
                <div class="hero__inner">
                    <div class="container">
                        <div class="wrap-text center text-large font-bold">
                            <h3 class="h2 mega-title">Chính sách bảo hành số 1 Việt Nam</h3>
                            <div class="rte-setting mega-subtitle">
                                Bảo hành 1 đổi 1
                                <%--									Nhận biết loại da qua bộ câu hỏi của </br> Dr. Leslie Baumann--%>
                            </div>
                            <%--								<a href="#" class="btn">Làm Skin Test ngay</a>--%>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--End Parallax Section-->

        <!--Featured Column-->
        <div class="section featured-column">
            <div class="container">
                <div class="row">
                    <div class="col-12 col-sm-12 col-md-12 col-lg-12">
                        <div class="section-header text-center">
                            <h2 class="h2">Bộ sưu tập</h2>
                            <p>Sản phẩm mới từ các thương hiệu nổi tiếng</p>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <!--Featured Item-->
                    <div class="col-12 col-sm-6 col-md-4 col-lg-4 text-center">
                        <p>
                            <a href="#"> <img class="blur-up lazyload"
                                              data-src="/assets/images/collection/top-view.jpg"
                                              src="/assets/images/collection/top-view.jpg"
                                              alt="feature-row__image">
                            </a>
                        </p>
                    </div>
                    <!--End Featured Item-->
                    <!--Featured Item-->
                    <div class="col-12 col-sm-6 col-md-4 col-lg-4 text-center">
                        <p>
                            <a href="#"> <img class="blur-up lazyload"
                                              data-src="/assets/images/collection/gamer-workspace.jpg"
                                              src="/assets/images/collection/gamer-workspace.jpg"
                                              alt="feature-row__image">
                            </a>
                        </p>
                    </div>
                    <!--End Featured Item-->
                    <!--Featured Item-->
                    <div class="col-12 col-sm-6 col-md-4 col-lg-4 text-center">
                        <p>
                            <a href="#"> <img class="blur-up lazyload"
                                              data-src="/assets/images/collection/banner.png"
                                              src="/assets/images/collection/banner.png"
                                              alt="feature-row__image">
                            </a>
                        </p>
                    </div>
                    <!--End Featured Item-->
                </div>
            </div>
        </div>
        <!--End Featured Column-->

        <!--Store Feature-->
        <div class="store-feature section">
            <div class="container">
                <div class="row">
                    <div class="col-12 col-sm-12 col-md-12 col-lg-12">
                        <ul class="display-table store-info">
                            <li class="display-table-cell"><i
                                    class="icon anm anm-truck-l"></i>
                                <h5>Miễn phí vận chuyển</h5> <span class="sub-text"> Đối
										với đơn từ 200k trong nội thành Hà Nội </span></li>
                            <li class="display-table-cell"><i
                                    class="icon anm anm-money-bill-ar"></i>
                                <h5>Nhiều khuyến mãi hấp dẫn</h5> <span class="sub-text">
										Hàng ngàn ưu đãi cực sốc được cập nhật mỗi ngày </span></li>
                            <li class="display-table-cell"><i
                                    class="icon anm anm-comments-l"></i>
                                <h5>Sản phẩm chính hãng</h5> <span class="sub-text"> Cam
										kết hoàn tiền 300% giá trị sản phẩm nếu phát hiện hàng không
										chính hãng </span></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <!--End Store Feature-->
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