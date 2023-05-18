let urlLogs = "/admin/rest/logs";
app.controller("log-ctrl", function ($scope, $http) {
    $scope.logs = [];
    $scope.log = {};
    $scope.pageSize = 10;
    //get danh sach don hang
    let impactTime = "";
    //LOAD DB
    $scope.init = function () {
        $http.get(`${urlLogs}/get-all?impactTime=${impactTime}`).then(resp => {
            $scope.logs = resp.data;
            $scope.totalRecords = $scope.logs.length;
        }).catch(error => {
            console.log(error);
        })
        $scope.start = 0;
    }

    $scope.next = function(){
        if($scope.start < $scope.logs.length - $scope.pageSize){
            $scope.start += $scope.pageSize;
        }
    }
    $scope.prev = function(){
        if($scope.start > 0){
            $scope.start -= $scope.pageSize;
        }
    }
    $scope.last = function(){
        var sotrang = Math.ceil($scope.logs.length / $scope.pageSize);
        $scope.start = (sotrang - 1) * $scope.pageSize;
    }
    $scope.first = function(){
        $scope.start = 0;
    }

    $scope.init();
    // $http.get(`${urlLogs}/get-all?impactTime=${impactTime}`).then(resp => {
    //     debugger
    //     console.log(resp)
    //     console.log(resp.data)
    // }).catch(error => {
    //     console.log(error);
    // })

    // $http.get(`${urlLogs}/find-all`).then(resp => {
    //     console.log("LOG " + resp.content)
    // }).catch(error => {
    //     console.log(error);
    // })
});