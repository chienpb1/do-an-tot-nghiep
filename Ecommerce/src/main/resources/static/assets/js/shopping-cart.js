var app = angular.module("shopping-app", []);
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
app.controller("shopping-ctrl", function ($scope, $http) {
    $scope.userDetail = {};
    $scope.data = {
        name: 'John',
        age: 30,
        address: 'New York'
    };
    $scope.submited = false;
    $scope.cart = {
        items: [],
        add(id) {
            var item = this.items.find(item => item.id == id);
            if (item) {
                item.qty++;
                this.saveToLocalStorage();
            } else {
                $http.get(`/rest/products/${id}`).then(resp => {
                    resp.data.qty = 1;
                    this.items.push(resp.data);
                    this.saveToLocalStorage();
                })
            }
        },
        minus(id) {
            var index = this.items.findIndex(item => item.id == id);
            if (this.items[index].qty == 1) {
                this.remove(id);
            } else {
                this.items[index].qty -= 1;
                this.saveToLocalStorage();
            }
        },
        remove(id) {
            var index = this.items.findIndex(item => item.id == id);
            this.items.splice(index, 1);
            this.saveToLocalStorage();
        },
        get count() {
            return this.items
                .map(item => item.qty)
                .reduce((total, qty) => total += qty, 0);
        },
        priceItem(id) {
            var index = this.items.findIndex(item => item.id == id);
            return this.items[index].qty * this.items[index].price;
        },
        get amount() {
            return this.items
                .map(item => item.qty * item.price)
                .reduce((total, price) => total += price, 0);
        },
        saveToLocalStorage() {
            var json = JSON.stringify(angular.copy(this.items));
            localStorage.setItem("cart", json);
        },
        clear() {
            this.items = [];
            this.saveToLocalStorage();
        },
        loadFromLocalStorage() {
            var json = localStorage.getItem("cart");
            this.items = json ? JSON.parse(json) : [];
        }
    }
    $scope.convertImage = function (json) {
        var lstImage = JSON.parse(json);
        return lstImage[0];
    }
    $scope.cart.loadFromLocalStorage();

    $scope.order = {
        address: "",
        account: {username: $("#username").text()},
        get orderDetails() {
            return $scope.cart.items.map(item => {
                return {
                    product: {id: item.id},
                    price: item.price,
                    quantity: item.qty
                }
            });
        },
        purchase() {
            var order = angular.copy(this);
            console.log(order);
            $http.post('/rest/order', order).then(resp => {
                var result = resp.data;
                if(result == 1) {
                    alert("Đặt hàng thành công");
                    $scope.cart.clear();
                    location.href = "/order/list";
                } else {
                    alert("Số lượng đặt vượt quá số lượng trong kho");
                }
            }).catch(error => {
                alert("Đặt hàng thất bại");
                console.log(error);
            });
        }
    }
    $scope.getAccount = function (){
        $http.get(`/get-my-account`).then(resp => {
            $scope.userDetail = resp.data;
            console.log("VAO DYA")
        });
    }

    $scope.getAccount();


    $scope.editMyAccount = function () {
        var username = $scope.userDetail.username;
        var url = `edit-my-account/${username}`;
        var data = angular.copy($scope.userDetail);
        $http.put(url, data).then(resp => {
            $scope.getAccount();
            alert("Cập nhật thông tin tài khoản thành công")
        }).catch(error => {
            if (error.status == 404) {
                alert("Not Exist Account " + username);
            }
        });
    };
});
app.controller("register", function ($scope, Shttp) {

});