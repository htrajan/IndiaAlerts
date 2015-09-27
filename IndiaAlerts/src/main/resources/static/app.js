// Defining angularjs application.
var postApp = angular.module('postApp', []);
// Controller function and passing $http service and $scope var.
postApp.controller('postController', function($scope, $http) {
	// create a blank object to handle form data.
	$scope.alert = {region: 'All'};
	// calling our submit function.
	$scope.submitForm = function() {
		// Posting data to rest service
		$http({
			method  : 'POST',
			url     : '/broadcast',
			data    : $scope.alert, //forms alert object
			headers : {'Content-Type': 'application/x-www-form-urlencoded'} 
		})
		.success(function(data) {
			if (data.errors) {
				// Showing errors.

			} else {
				$scope.message = data.message;
			}
		});
	};
});