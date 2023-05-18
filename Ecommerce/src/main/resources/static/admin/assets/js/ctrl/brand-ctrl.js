let urlBrand = "/admin/rest/brands";
app.controller("brand-ctrl", function ($scope, $http) {
    $scope.brands = [];
    $scope.brand = {};
    $scope.idDelete = '';
    $scope.chon = false;
    $scope.pageSize = 10;
    $scope.init = function (){
        $http.get(urlBrand).then(resp => {
            $scope.brands = resp.data;
            $scope.totalRecords = $scope.brands.length;
        });
        $scope.start = 0;
    }

    $scope.next = function(){
        if($scope.start < $scope.brands.length - $scope.pageSize){
            $scope.start += $scope.pageSize;
        }
    }
    $scope.prev = function(){
        if($scope.start > 0){
            $scope.start -= $scope.pageSize;
        }
    }
    $scope.last = function(){
        var sotrang = Math.ceil($scope.brands.length / $scope.pageSize);
        $scope.start = (sotrang - 1) * $scope.pageSize;
    }
    $scope.first = function(){
        $scope.start = 0;
    }

    //HAM KHOI DAU
    $scope.init();
    //get 1 brand
    $scope.edit = function (id) {
        var url = `${urlBrand}/${id}`;
        $http.get(url).then(resp => {
            $scope.brand = resp.data;
            $scope.chon = true;
        }).catch(error => {
            if (error.status == 404) {
                alert("Không tồn tại brand " + $scope.brand.id);
            }
        });
    };
    //upload img
    $scope.imageChanged = function (files) {
        var url = "/admin/rest/upload/brand"
        var form = new FormData();
        form.append("file", files[0]);
        $http.post(url, form, {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        }).then(resp => {
            $scope.brand.image = resp.data.filename;
        }).catch(error => {
            console.log("error", error);
        });
    };
    $scope.update = function (id) {
        var url = `${urlBrand}/${id}`;
        var data = angular.copy($scope.brand);
        var index = $scope.brands.findIndex(b => b.id == id);
        $http.put(url, data).then(resp => {
            alert("Cập nhật thương hiệu thành công");
            $scope.init();
        }).catch(error => {
            if (error.status == 404) {
                alert("Không tồn tại brand " + $scope.brand.id);
            }
        });
    };
    $scope.create = function () {
        var data = angular.copy($scope.brand);
        $http.post(urlBrand, data).then(resp => {
            alert("Thêm mới thương hiệu thành công");
            $scope.init();
            $scope.reset();
        }).catch(error => {
            if (error.status == 400) {
                alert("Đã tồn tại brand " + $scope.brand.id);
            }
            console.log("error ", error);
        });
    };
    $scope.reset = function () {
        $scope.brand = {
            id: "",
            name: "",
            image: "logo.png",
            available: true
        };
        $scope.chon = false;
    }
    $scope.handleButtonClick = function (id) {
        $scope.idDelete = '';
        $scope.idDelete = id;
    }

    $scope.delete = function () {
        let modal = document.getElementById("close-btn-brand");
        // Hàm xử lý xóa
        let id = $scope.idDelete;
        var url = `${urlBrand}/${id}`;
        $http.delete(url).then(resp => {
            $scope.init();
            alert("Xóa thương hiệu thành công");
            $scope.reset();
            modal.click();
        }).catch(error => {
            if (error.status == 404) {
                alert("Không tồn tại brand " + id);
            }
        });
    };
    $scope.search = function (kw) {
        if (kw != null) {
            var url = `${urlBrand}/search?kw=${kw}`;
            $http.get(url).then(resp => {
                $scope.brands = resp.data;
            });
        }
    };
    $scope.reset();
});


