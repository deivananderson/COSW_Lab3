'use strict';

angular.module('myApp.clients', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/clients', {
    templateUrl: 'clients/clients.html',
    controller: 'ClientsCtrl'
  });
}])

.controller('ClientsCtrl',['Clients','$scope','$mdDialog',function(Clients,$scope,$mdDialog){
    Clients.get(function(data){
        $scope.clientList = data;
    })


  $scope.showAlert = function(ev,cl) {
    // Appending dialog to document.body to cover sidenav in docs app
    // Modal dialogs should fully cover application
    // to prevent interaction outside of dialog
    $mdDialog.show(
      $mdDialog.alert()
        .parent(angular.element(document.querySelector('#popupContainer')))
        .clickOutsideToClose(true)
        .title(cl.name)
        .textContent(cl.profileDescription)
        .ariaLabel('Alert Dialog Demo')
        .ok('Got it!')
        .targetEvent(ev)
    );
  };


}]);