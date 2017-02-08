(function() {
  var app = angular.module('app', []);
  app.controller('taskCtrl', TaskCtrl);
  console.log(app);

  TaskCtrl.$inject = ['$http'];
  function TaskCtrl($http) {
    console.log($http);
    this.tasks = [];
    var that = this;
    $http.get('api/tasks').then(function(tasks){console.log(tasks.data); that.tasks = tasks.data;})
  }
})();