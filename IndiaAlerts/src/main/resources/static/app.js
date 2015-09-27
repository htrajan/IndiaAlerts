// Defining angularjs application.
var postApp = angular.module('postApp', []);

// Controller function and passing $http service and $scope var.
postApp.controller('postController', function($scope, $http) {
	// create a blank object to handle form data.
	$scope.alert = {region: 'All India'};
	// calling our submit function.
	$scope.submitForm = function() {
		// Posting data to rest service
		$http({
			method  : 'POST',
			url     : '/broadcastAlert',
			data    : $scope.alert, //forms alert object
			headers : {"Content-Type": "application/json"} 
		})
		.success(function(data) {
		});
	};
});