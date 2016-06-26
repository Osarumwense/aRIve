/**
 * Created by 37264 on 6/26/16.
 */

$(document).ready(function () {
    document.getElementById("submit").addEventListener("click", login);
});

function login() {
var dest = $('#dest').val();
//     var dest = "hello";
var busID = $('#bus').val();
    var busNumber = $('#busNumber').val();
var formData = {destination:dest,busNumber:busID};

$.ajax({
    url : "/index.html",
    type: "POST",
    data : {
        destination: dest,
        busID: busID,
        busNumber: busNumber
    },
    success: function(data, textStatus, jqXHR)
    {
        //data - response from server
    },
    error: function (jqXHR, textStatus, errorThrown)
    {

    }
});
}
