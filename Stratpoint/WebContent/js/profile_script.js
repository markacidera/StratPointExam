var profilelist_url = 'http://localhost:8080/Stratpoint/rest/strat/profile';


$(document).ready(function () {
    //anything in here will only be called/work when the document is ready.
    setUpHomePage();
    //call your function in here, instead of bodyonload
});

function setUpHomePage() {

    var container = document.createElement('div');
    container.setAttribute("class", "container");

    var search_div = document.createElement('div');
    var search_btn = document.createElement('button');

    search_btn.setAttribute("type", "button");
    search_btn.setAttribute("class", "button");
    search_btn.setAttribute("id", "search_btn");

    search_btn.innerHTML = "SEARCH";
    var search_fld = document.createElement('input');
    search_fld.setAttribute("id", "search_fld");

    search_fld.setAttribute("placeholder", "Search Name...");
    search_div.appendChild(search_fld);
    search_div.appendChild(search_btn);
    container.appendChild(search_div);
    document.body.appendChild(container);
    populateProfileTable(profilelist_url);

    $("#search_btn").click(function () {
        searchName();
    });
}

function searchName() {
    var name = $('#search_fld').val();

    $("#profile_table tr").remove();
    populateProfileTable(profilelist_url + "/" + name);
}

function populateProfileTable(url) {


    $.ajax({
        type: 'GET',
        url: url,
        success: function (profilelist) {
            createTable(profilelist);
            console.log("success", profilelist);
        },
        error: function () {
            alert("Error Loading Profile...");
        }
    });


}

function createTable(profilelist) {
    var headerList;
    var divTbl = document.createElement("div");
    // divTbl.setAttribute("class", "table-responsive");
    var profileTbl = document.createElement("table")


    // profileTbl.setAttribute("class", "table table-border table-striped")
    profileTbl.setAttribute("id", "profile_table")
    $.each(profilelist, function (i, profile) {
        headerList = Object.keys(profile);
        if (i == 0) {
            console.log(Object.keys(profile));

            var trHeader = document.createElement("tr");
            $.each(headerList, function (i, header) {
                var th = document.createElement("th");
                th.innerHTML = header;
                trHeader.appendChild(th);
                profileTbl.appendChild(trHeader);
            })
        }
        var trValue = document.createElement("tr");
        // var profileJson = $.parseJSON(profile);

        $.each(headerList, function (i, key) {
            var td = document.createElement("td");
            if (key == "name") {
                td.innerHTML = profile[key].first + " " + profile[key].middle + ". " + profile[key].last
            } else {
                td.innerHTML = profile[key];

            }
            trValue.appendChild(td);
            profileTbl.appendChild(trValue);

        })
    })
    divTbl.appendChild(profileTbl);
    document.body.appendChild(divTbl);
}