(function() {
  var app = angular.module('app', []);
  app.controller('taskCtrl', TaskCtrl);
  console.log(app);

  TaskCtrl.$inject = ['$http'];
  function TaskCtrl($http) {
    this.$http = $http;
    this.tasks = [];
    var that = this;
    this.newTaskValue = '';
    $http.get('api/tasks').then(function(tasks){console.log(tasks.data); that.tasks = tasks.data;})
  }

  TaskCtrl.prototype.addNewTask = function(taskText) {
    console.log(taskText);
    this.newTaskValue = '';
    var newTask = {
      id: 0,
      text: taskText
    };

    var that = this;
    this.$http.post('api/tasks', newTask).then(function(res) {
      var savedTask = res.data.newTask;
      that.tasks.unshift(savedTask);
    })
  }


})();