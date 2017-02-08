(function() {
  var app = angular.module('app', []);
  app.controller('taskCtrl', TaskCtrl);
  console.log(app);

  TaskCtrl.$inject = ['$http'];
  function TaskCtrl($http) {
    console.log($http);
    this.blah = "bleh";
  }
})();