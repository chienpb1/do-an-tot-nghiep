let urlDetailOrder = "/rest/order/get-detail";
let urlConfirmOrder = "/rest/order/confirm-order";
let urlDeleteOrder = "/rest/order/delete-order";
app.filter('customNumber', function () {
    return function (input) {
        // Chuyển đổi giá trị sang kiểu số
        var value = parseFloat(input);

        // Kiểm tra nếu giá trị là số và không phải là NaN
        if (!isNaN(value)) {
            // Định dạng giá trị với dấu chấm phân cách hàng nghìn
            var formattedValue = value.toLocaleString('vi-VN', { maximumFractionDigits: 0 });

            return formattedValue;
        }

        // Trả về giá trị gốc nếu không thể định dạng
        return input;
    };
});
app.controller("order-ctrl",function($scope, $http){
    $scope.orders = [];
    $scope.order = {};
    $scope.idClick = '';
    $scope.orderDetail = {};
    $scope.pageSize = 10;
    //get danh sach don hang
    $scope.init = function () {
        $http.get("/rest/order/get-all").then(resp => {
            $scope.orders = resp.data;
            $scope.totalRecords = $scope.orders.length;
        });
        $scope.start = 0;
    }
    $scope.next = function(){
        if($scope.start < $scope.orders.length - $scope.pageSize){
            $scope.start += $scope.pageSize;
        }
    }
    $scope.prev = function(){
        if($scope.start > 0){
            $scope.start -= $scope.pageSize;
        }
    }
    $scope.last = function(){
        var sotrang = Math.ceil($scope.orders.length / $scope.pageSize);
        $scope.start = (sotrang - 1) * $scope.pageSize;
    }
    $scope.first = function(){
        $scope.start = 0;
    }

    //HAM KHOI DAU
    $scope.init();

    $scope.search = function(kw){
        if(kw != null){
            var url = `/rest/order/search?kw=${kw}`;
            $http.get(url).then(resp => {
                $scope.orders = resp.data;
            });
        }
    };

    $scope.detail = function(id){
        $http.get(`${urlDetailOrder}/${id}`).then(resp => {
            $scope.orderDetail = resp.data;
        });
    };

    $scope.handleButtonClick = function (id) {
        $scope.idClick = '';
        $scope.idClick = id;
    }

    $scope.edit = function(){
        let modal = document.getElementById("close-btn-confirm-order");
        let id = $scope.idClick;
        $http.put(`${urlConfirmOrder}/${id}`).then(resp => {
            $http.get("/rest/order/get-all").then(resp => {
                $scope.orders = resp.data;
            });
            modal.click();
        });
    };

    $scope.delete = function(){
        let modal = document.getElementById("close-btn-delete-order");
        let id = $scope.idClick;
        $http.put(`${urlDeleteOrder}/${id}`).then(resp => {
            $http.get("/rest/order/get-all").then(resp => {
                $scope.orders = resp.data;
            });
            modal.click();
        });
    };
});