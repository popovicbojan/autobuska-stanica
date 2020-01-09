var stanicaApp = angular.module("stanicaApp", ["ngRoute"]);

stanicaApp.controller("LinijaCtrl", function ($scope, $http, $location) {
    $scope.message = "Autobuska stanica Novi Sad";

    var urlLinije = "/api/linije";
    var urlPrevoznici = "api/prevoznici";

    $scope.linije = [];
    $scope.prevoznici = [];

    //nova linija
    $scope.novaLinija = {};
    $scope.novaLinija.brojMesta = "";
    $scope.novaLinija.cenaKarte = "";
    $scope.novaLinija.destinacija = "";
    $scope.novaLinija.vremePolaska = "";
    $scope.novaLinija.idPrevoznika = "";

    //pretraga
    $scope.sParams = {};
    $scope.sParams.destinacija = "";
    $scope.sParams.prevoznik = "";
    $scope.sParams.maxCena = "";

    //broj strana
    $scope.pageNum = 0;
    $scope.totalPages = 1;


    var getLinije = function () {

        var config = {
            params: {}
        };

        if ($scope.sParams.destinacija != "") {
            config.params.destinacija = $scope.sParams.destinacija;
        }

        if ($scope.sParams.prevoznik != "") {
            config.params.prevoznik = $scope.sParams.prevoznik;
        }

        if ($scope.sParams.maxCena != "") {
            config.params.maxCena = $scope.sParams.maxCena;
        }

        config.params.page = $scope.pageNum;

        var promise = $http.get(urlLinije, config);
        promise.then(
            function (res) {
                $scope.totalPages = res.headers("ukupnoStrana");
                $scope.linije = res.data;
            },
            function (res) {
                alert("Neuspesno dobavljanje linija!");
            }
        );

    };

    getLinije();


    var getPrevoznici = function () {
        var promise = $http.get(urlPrevoznici);

        promise.then(
            function (res) {
                $scope.prevoznici = res.data;
            },
            function (res) {
                alert("Neuspesno dobavljanje prevoznika!");
            }
        );
    };

    getPrevoznici();

    $scope.doAdd = function () {
        var promise = $http.post(urlLinije, $scope.novaLinija);

        promise.then(
            function (res) {
                getLinije();
                //brisanje vrednosti iz polja
                $scope.novaLinija = {};
                $scope.novaLinija.brojMesta = "";
                $scope.novaLinija.cenaKarte = "";
                $scope.novaLinija.destinacija = "";
                $scope.novaLinija.vremePolaska = "";
                $scope.novaLinija.idPolaznika = "";
            },
            function (res) {
                alert("Neuspesno dodavanje linije!");
            }
        );
    };

    $scope.goToEdit = function (id) {
        $location.path('/linije/edit/' + id);
    };

    $scope.doDelete = function (id) {
        var promise = $http.delete(urlLinije + '/' + id);
        promise.then(
            function (res) {
                getLinije();
            },
            function (res) {
                alert("Neuspesno brisanje linije!");
            }
        );
    };

    $scope.doSearch = function () {
        getLinije();
    };

    $scope.changePage = function (direction) {
        $scope.pageNum += direction;
        getLinije();
    };

    $scope.goToRezervisi = function (id) {
        var rezervacija = {};

        rezervacija.idLinije = id;
        rezervacija.brojMesta = 1;

        var promise = $http.post("api/rezervacije", rezervacija);

        promise.then(
            function (res) {
                alert("Rezervacija mesta za destinaciju " + res.data.destinacija + " prevoznika " + res.data.nazivPrevoznika +
                    " je uspesno obavljena!");
                getLinije();
            },
            function (res) {
                alert("Neuspesna rezervacija!");
            }
        );
    };

});

stanicaApp.controller('EditLinijaCtrl', function ($scope, $http, $routeParams, $location) {

    var urlLinije = "/api/linije/";
    var urlJedneLinije = urlLinije + $routeParams.id;
    var urlPrevoznici = "api/prevoznici";

    $scope.prevoznici = [];

    $scope.linija = {};
    $scope.linija.brojMesta = "";
    $scope.linija.cenaKarte = "";
    $scope.linija.destinacija = "";
    $scope.linija.vremePolaska = "";
    $scope.linija.idPolaznika = "";


    var getPrevoznici = function () {
        var promise = $http.get(urlPrevoznici);

        promise.then(
            function (res) {
                $scope.prevoznici = res.data;
                getLinija();
            },
            function (res) {
                alert("Neuspesno dobavljanje prevoznika!");
            }
        );
    };

    getPrevoznici();

    var getLinija = function () {
        var promise = $http.get(urlJedneLinije);
        promise.then(
            function (res) {
                $scope.linija = res.data;
            },
            function (res) {
                alert("Neuspesno dobavljanje linije!");
            }
        );
    };


    $scope.doEdit = function () {
        var promise = $http.put(urlJedneLinije, $scope.linija);

        promise.then(
            function (res) {
                $location.path('/');
            },
            function (res) {
                alert("Neuspesno editovanje linije!");
            }
        );
    }

});


stanicaApp.controller('RezervacijaCtrl', function ($scope, $http) {

    $scope.rezervacije = [];

    var getRezervacije = function () {
        var promise = $http.get('api/rezervacije');
        promise.then(
            function (res) {
                $scope.rezervacije = res.data;
            },
            function (res) {
                alert("Neuspesno dobavljanje rezervacija!");
            }
        );
    };

    getRezervacije();
});

stanicaApp.config(['$routeProvider', function ($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl: 'app/html/linija.html',
            controller: 'LinijaCtrl'
        })
        .when('/linije/edit/:id', {
            templateUrl: "app/html/edit-linija.html",
            controller: "EditLinijaCtrl"
        })
        .when('/rezervacije', {
            templateUrl: "app/html/rezervacija.html",
            controller: "RezervacijaCtrl"
        })
        .otherwise({
            redirectTo: "/"
        });
}]);