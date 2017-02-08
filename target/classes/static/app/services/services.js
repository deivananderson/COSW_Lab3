'use strict';
angular.module('services.factory', ['ngRoute','ngResource'])

    .factory('fabrica', function () {
        var data = {
            listado: []
        };
        return {
            getListado: function () {
                return data.listado;
            },
            addTodo: function (todo) {
                data.listado.push(todo);
            }};
    })

     .factory('tareas', function($resource) {
             return $resource('/tareas/:tarea');
         });