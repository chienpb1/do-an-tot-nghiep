const numberFormat = new Intl.NumberFormat('vi-VN', {
  style: 'currency',
  currency: 'VND',
});
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
app.controller("dashboard-ctrl", function ($scope, $http) {
  $scope.total = [];
  $scope.costInMonth = [];
  $scope.costDate = [];
  $scope.costData = [];
  $scope.orderData = [];

  $scope.productInMonth = [];
  $scope.productName = [];
  $scope.productCount = [];

  $http.get('/admin/rest/report/total').then(resp => {
    $scope.total = resp.data;
  }).catch(error => {
    alert("Load total data fail");
    console.log(error);
  });

  $http.get('/admin/rest/report/reportcost').then(resp => {
    $scope.costInMonth = resp.data;
    $scope.costInMonth.forEach(c => c.date = new Date(c.date));
    for (var i = 0; i < $scope.costInMonth.length; i++) {
      $scope.costDate.push($scope.costInMonth[i].date.getDate());
      $scope.costData.push($scope.costInMonth[i].cost);
      $scope.orderData.push($scope.costInMonth[i].sumOrder);
      if($scope.costInMonth[i].date.getDate() == (new Date()).getDate()){
        $scope.costToday = $scope.costToday + $scope.costInMonth[i].cost;
        $scope.orderToday = $scope.orderToday + $scope.costInMonth[i].sumOrder;
      }else{
        $scope.costToday = 0;
        $scope.orderToday = 0;
      }
    }
    console.log( (new Date()).getDate())
  }).catch(error => {
    alert("Load cost data fail");
    console.log(error);
  });

  $http.get('/admin/rest/report/bestSellerInMonth').then(resp => {
    $scope.productInMonth = resp.data;
    var qty = 0;
    if($scope.productInMonth.length > 5){
      qty = 5;
    } else {
      qty = $scope.productInMonth.length;
    }
    for(var i = 0; i < qty; i++){
      $scope.productName.push($scope.productInMonth[i].name);
      $scope.productCount.push($scope.productInMonth[i].count);
    }
  });

  $scope.reportCost = function () {
    // let date = (new Date()).toLocaleString('default', { month: 'short' });
    let options = { month: 'long' };
    let date = (new Date()).toLocaleString('vi-VN', options);
    const data = {
      labels: $scope.costDate,
      datasets: [
        {
        label: 'Doanh thu ',
        data: $scope.costData,
        fill: false,
        borderColor: 'rgb(75, 192, 192)',
        tension: 0.1
      }
    ]
    };

    const config = {
      type: 'line',
      data: data,
    };
    const myChart = new Chart(
      document.getElementById('costChart'),
      config
    );
  }
  $scope.reportProduct = function(){
    let date = (new Date()).toLocaleString('default', { month: 'short' });
    const data = {
      labels: $scope.productName,
      datasets: [
        {
        label: 'Best seller in '+date,
        data: $scope.productCount,
        fill: false,
        backgroundColor: [
          'rgb(255, 99, 132)',
          'rgb(54, 162, 235)',
          'rgb(255, 205, 86)',
          'rgb(4, 76, 153 )',
          'rgb(181, 216, 253 )',
        ],
        hoverOffset: 4
      }
    ]
    };

    const config = {
      type: 'pie',
      data: data,
    };
    const bestSeller = new Chart(
      document.getElementById('bestSeller'),
      config
    );
  }
  $scope.reportProduct();
  $scope.reportCost();

});

