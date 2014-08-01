(function() {
    var as = angular.module('myApp.controllers', []);
    as.controller('AppCtrl',['$scope', '$rootScope', '$http', 'i18n', '$location', 'apiUrl', function($scope, $rootScope, $http, i18n, $location, apiUrl) {
        $scope.language = function() {
            return i18n.language;
        };
        $scope.setLanguage = function(lang) {
            i18n.setLanguage(lang);
        };
        $scope.activeWhen = function(value) {
            return value ? 'active' : '';
        };

        $scope.path = function() {
            return $location.url();
        };

        $scope.login = function() {
            $scope.$emit('event:loginRequest', $scope.username, $scope.password);
        };

        $scope.logout = function() {
            $rootScope.user = null;
            $scope.username = $scope.password = null;
            $scope.$emit('event:logoutRequest');
        };


    }]);

    as.controller('ExpCtrl', ['$scope', '$rootScope', '$http', '$location', 'apiUrl', function($scope, $rootScope, $http, $location, apiUrl) {
        var load = function() {
            console.log('call load() in ExpCtrl ...');
            $http.get(apiUrl + '/exps??.json')
                    .success(function(data, status, headers, config) {
                        $scope.experiments = data;
                        angular.copy($scope.experiments, $scope.copy);
                    });
        }
        load();

        $scope.showExpDetails = function() {
            console.log('call showExpDetails');
            $location.path("/expDetails/" + $scope.experiments[index].id);
        }

        $scope.showDataRows = function(index) {
            console.log('call showDataRows');
            $location.path('/dataRows/' + $scope.experiments[index].id);
        }
    }]);

    as.controller('DataRowCtrl', ['$scope', '$rootScope', '$http', '$location', 'apiUrl', function($scope, $rootScope, $http, $location, apiUrl) {

        $scope.standardDataRowFields = [
            "activityR1Ch1",
            "activityR1Ch2",
            "activityR2Ch1",
            "activityR2Ch2",
            "bioModel",
            "contributorExperiment",
            "finalWellAnno",
            "image",
            "library",
            "libraryProvider",
            "normalizedR1",
            "normalizedR2",
            "phenotype",
            "plate",
            "position",
            "primaryGeneID",
            "rawR1Ch1",
            "rawR1Ch2",
            "rawR2Ch1",
            "rawR2Ch2",
            "reagentId",
            "reagentName",
            "score",
            "well",
            "wellAnno"
        ];

        var load = function() {
            console.log('call load() in DataRowCtrl ...');
            $http.get(apiUrl + '/dataRows???.json')
                .success(function(data, status, headers, config) {
                    $scope.dataRows = data;
                    for(var i = 0; i < $scope.dataRows.length; i++) {

                    }
                    angular.copy($scope.dataRows, $scope.copy);
                });
        }
        load();
    }]);
}());