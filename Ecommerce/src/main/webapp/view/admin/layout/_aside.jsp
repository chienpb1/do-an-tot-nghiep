<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="security"
           uri="http://www.springframework.org/security/tags"%>
<aside class="app-navbar">
    <!-- begin sidebar-nav -->
    <div class="sidebar-nav scrollbar scroll_light">
        <ul class="metismenu " id="sidebarNav">
            <li class="active">
                <a class="has-arrow" href="#" aria-expanded="false">
                    <i class="nav-icon ti ti-rocket"></i>
                    <span class="nav-title">Trang chủ</span>
                </a>
            </li>
            <li><a href="#!brand" aria-expanded="false"><i class="fa-solid fa-address-book"></i><span class="nav-title">Thương hiệu</span></a>
            </li>
            <li><a href="#!category" aria-expanded="false"><i class="fa-solid fa-boxes-stacked"></i><span
                    class="nav-title">Danh mục</span></a></li>
            <li><a href="#!product" aria-expanded="false"><i class="fas fa-box-open"></i><span class="nav-title">Sản phẩm</span></a>
            </li>
            <li><a href="#!order" aria-expanded="false"><i class="fas fa-file-invoice-dollar"></i><span
                    class="nav-title">Đơn hàng</span></a></li>
            <c:if test="${(not empty sessionScope.userAdmin) && (empty sessionScope.userStaff)}">
                <li><a href='#!user'><i class="fa-solid fa-file-pen"></i><span class="nav-title">Người dùng</span></a></li>
            </c:if>
            <c:if test="${(not empty sessionScope.userAdmin) && (empty sessionScope.userStaff)}">
                <li><a href='#!log'><i class="fa-solid fa-anchor"></i><span class="nav-title">Nhật ký tác động</span></a></li>
            </c:if>
        </ul>
    </div>
    <!-- end sidebar-nav -->
</aside>