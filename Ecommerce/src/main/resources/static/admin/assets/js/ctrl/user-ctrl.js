let urlAccount = "/admin/rest/accounts";
app.controller("user-ctrl", function ($scope, $http, $window) {

    $scope.accounts = [];
    $scope.account = {};
    $scope.username = '';
    $scope.currentUsername = '';
    $scope.usernameDelete = '';
    $scope.pageSize = 10;
    $scope.chon = false;
    $scope.listRole = [
        {
            role: "user",
            description: "user"
        },
        {
            role: "staff",
            description: "staff"
        },
        {
            role: "director",
            description: "director"
        }
    ]

    $http.get(`${urlAccount}/current-username`).then(resp => {
        let obj = resp.data;
        $scope.currentUsername = obj.username;
    });

    $scope.init = function () {
        $http.get(urlAccount).then(resp => {
            $scope.accounts = resp.data;
            $scope.totalRecords = $scope.accounts.length;
        });
        $scope.start = 0;
    }
    $scope.next = function () {
        if ($scope.start < $scope.accounts.length - $scope.pageSize) {
            $scope.start += $scope.pageSize;
        }
    }
    $scope.prev = function () {
        if ($scope.start > 0) {
            $scope.start -= $scope.pageSize;
        }
    }
    $scope.last = function () {
        var sotrang = Math.ceil($scope.accounts.length / $scope.pageSize);
        $scope.start = (sotrang - 1) * $scope.pageSize;
    }
    $scope.first = function () {
        $scope.start = 0;
    }
    //get danh sach accounts
    $scope.init();

    // $scope.checkAdmin = function (username) {
    //     var url = `${urlAccount}/${username}`;
    //     $http.get(url).then(resp => {
    //         if(resp.data.role.role == "director"){
    //             return true;
    //         }
    //     }).catch(error => {
    //         if(error.status == 404){
    //             alert("Not Exist accounts "+username);
    //         }
    //     });
    //     return false;
    // }
    //get 1 Account
    $scope.edit = function (username) {
        var url = `${urlAccount}/${username}`;
        $http.get(url).then(resp => {
            $scope.account = resp.data;
            $scope.chon = true;
        }).catch(error => {
            if (error.status == 404) {
                alert("Not Exist accounts " + username);
            }
        });
    };

    $scope.getAccount = function () {
        var url = `my-account`;
        $http.get(url).then(resp => {
            $window.location.href = '/my-account';
        }).catch(error => {
            if (error.status == 404) {
                alert("Not Exist accounts " + username);
            }
        });
    };
    //upload img
    $scope.imageChanged = function (files) {
        var url = "/admin/rest/upload/account"
        var form = new FormData();
        form.append("file", files[0]);
        $http.post(url, form, {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        }).then(resp => {
            $scope.account.photo = resp.data.filename;
        }).catch(error => {
            alert("Upload Photo Fail")
            console.log("error", error);
        });
    };
    //update account
    $scope.update = function (username) {
        var url = `${urlAccount}/${username}`;
        var data = angular.copy($scope.account);
        var index = $scope.accounts.findIndex(a => a.username == username);
        $http.put(url, data).then(resp => {
            $scope.accounts[index] = resp.data;
            $scope.init();
            alert("Cập nhật người dùng thành công")
        }).catch(error => {
            if (error.status == 404) {
                alert("Not Exist Account " + username);
            }
        });
    };
    //tao account
    $scope.create = function () {
        var data = angular.copy($scope.account);
        $http.post(urlAccount, data).then(resp => {
            $scope.accounts.push(resp.data);
            $scope.init();
            alert("Create Account Success")
            $scope.reset();
        }).catch(error => {
            if (error.status == 400) {
                alert("Existed Account " + data.username);
            }
            console.log("error ", error);
        });
    };
    //Rest khi load, khi tao moi thanh cong, khi delete, khi click btn reset
    $scope.reset = function () {
        $scope.account = {
            role: true,
            photo: "logo.png"
        };
        $scope.chon = false;
    }

    $scope.handleButtonClick = function (username) {
        $scope.usernameDelete = '';
        $scope.usernameDelete = username;
    }
    //Xoa account
    $scope.delete = function () {
        let modal = document.getElementById("close-btn");
        // let button1 = document.querySelector('[data-target="#myModalUser"]');
        // Hàm xử lý xóa
        // let username = button1.dataset.id;
        let username = $scope.usernameDelete;
        console.log("USERNAME + " + username)
        var url = `${urlAccount}/${username}`;
        $http.delete(url).then(resp => {
            $scope.init();
            // var index = $scope.accounts.findIndex(a => a.username == username);
            // $scope.accounts.splice(index, 1);
            alert("Xóa người dùng thành công");
            $scope.reset();
            modal.click();
        }).catch(error => {
            if (error.status == 404) {
                alert("Not Exist Account " + username);
            }
        });
    };
    $scope.search = function (kw) {
        if (kw != null) {
            var url = `${urlAccount}/search?kw=${kw}`;
            $http.get(url).then(resp => {
                $scope.accounts = resp.data;
            });
        }
    };
    $scope.reset();
});