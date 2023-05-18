let urlCategory = "/admin/rest/categories";
app.controller("category-ctrl", function ($scope, $http) {
    $scope.cates = [];
    $scope.cate = {};
    $scope.chon = false;
    $scope.pageSize = 10;
    //get danh sach Category
    $scope.init = function (){
        $http.get(urlCategory).then(resp => {
            $scope.cates = resp.data;
            $scope.totalRecords = $scope.cates.length;
        });
        $scope.start = 0;

    }

    $scope.init();


    $scope.next = function(){
        if($scope.start < $scope.cates.length - $scope.pageSize){
            $scope.start += $scope.pageSize;
        }
    }
    $scope.prev = function(){
        if($scope.start > 0){
            $scope.start -= $scope.pageSize;
        }
    }
    $scope.last = function(){
        var sotrang = Math.ceil($scope.cates.length / $scope.pageSize);
        $scope.start = (sotrang - 1) * $scope.pageSize;
    }
    $scope.first = function(){
        $scope.start = 0;
    }

    //HAM KHOI DAU


    //get 1 Category
    $scope.edit = function (id) {
        var url = `${urlCategory}/${id}`;
        $http.get(url).then(resp => {
            $scope.cate = resp.data;
            $scope.chon = true;
        }).catch(error => {
            if (error.status == 404) {
                alert("Không tồn tại category " + $scope.cate.id);
            }
        });
    };
    $scope.update = function (id) {
        var url = `${urlCategory}/${id}`;
        var data = angular.copy($scope.cate);
        var index = $scope.cates.findIndex(c => c.id == id);
        $http({
            method: 'PUT',
            url: url,
            data: data,
            headers: {'Content-Type': 'application/json'}
        }).then(function successCallback(response) {
            // Xử lý dữ liệu trả về từ Backend
            $scope.cates[index] = response.data;
        }, function errorCallback(response) {
            // Xử lý lỗi khi gửi yêu cầu tới Backend
            if (response.status == 404) {
                alert("Không tồn tại category " + $scope.cate.id);
            }
        });
        // $http.put(url, data).then(resp => {
        //     $scope.cates[index] = resp.data;
        // }).catch(error => {
        //     if(error.status == 404){
        //         alert("Không tồn tại category "+$scope.cate.id);
        //     }
        // });
    };
    $scope.create = function () {
        var data = angular.copy($scope.cate);
        $http.post(urlCategory, data).then(resp => {
            $scope.cates.push(resp.data);
            $scope.reset();
        }).catch(error => {
            if (error.status == 400) {
                alert("Đã tồn tại category " + $scope.cate.id);
            }
            console.log("error ", error);
        });
    };
    $scope.reset = function () {
        $scope.cate = {
            id: "",
            name: "",
            available: true
        };
        $scope.chon = false;
    }
    $scope.delete = function (id) {
        var url = `${urlCategory}/${id}`;
        $http.delete(url).then(resp => {
            var index = $scope.cates.findIndex(c => c.id == id);
            $scope.cates.splice(index, 1);
            $scope.reset();
        }).catch(error => {
            if (error.status == 404) {
                alert("Không tồn tại category " + $scope.cate.id);
            }
        });
    };
    $scope.search = function (kw) {
        if (kw != null) {
            var url = `${urlCategory}/search?kw=${kw}`;
            $http.get(url).then(resp => {
                $scope.cates = resp.data;
            });
        }
    };
    $scope.reset();
});