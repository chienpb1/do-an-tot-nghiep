app.controller("order-ctrl",function($scope, $http){
    $scope.orders = [];
    $scope.order = {};
    //get danh sach don hang
    $http.get("/rest/order/get-all").then(resp => {
        $scope.orders = resp.data;
    });
    console.log($scope.orders)
    //get 1 brand
    // $scope.edit = function(id){
    //     var url = `${urlBrand}/${id}`;
    //     $http.get(url).then(resp => {
    //         $scope.brand = resp.data;
    //         $scope.chon = true;
    //     }).catch(error => {
    //         if(error.status == 404){
    //             alert("Không tồn tại brand "+$scope.brand.id);
    //         }
    //     });
    // };

    // $scope.reset = function(){
    //     $scope.brand = {
    //         id: "",
    //         name: "",
    //         image: "logo.jpg"
    //     };
    //     $scope.chon = false;
    // }
    // $scope.delete = function(id){
    //     var url = `${urlBrand}/${id}`;
    //     $http.delete(url).then(resp => {
    //         var index = $scope.brands.findIndex(b => b.id == id);
    //         $scope.brands.splice(index, 1);
    //         $scope.reset();
    //     }).catch(error => {
    //         if(error.status == 404){
    //             alert("Không tồn tại brand "+$scope.brand.id);
    //         }
    //     });
    // };
    $scope.search = function(kw){
        if(kw != null){
            var url = `/rest/order/search?kw=${kw}`;
            $http.get(url).then(resp => {
                $scope.orders = resp.data;
            });
        }
    };
});