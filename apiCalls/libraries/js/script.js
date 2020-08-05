    $('#goBtn').click(function(){

        $(this).attr("disabled", "disabled");

        $.ajax({
            url: "libraries/php/getCountryInfo.php",
            type: 'POST',
            dataType: 'json',
            data: {
                country: $('#countrySelect').val(),
                lang: $('#languageSelect').val()
            },
            success: function(result){

                console.log(result);

                if(result.status.name == "ok"){
                    $('#table1').css("visibility", "visible");
                    $('#continent').html(result['data'][0]['continent']);
                    $('#capital').html(result['data'][0]['capital']);
                    $('#languages').html(result['data'][0]['languages']);
                    $('#population').html(result['data'][0]['population']);
                    $('#area').html(result['data'][0]['areaInSqKm']);
                }
            },
            error: function(jqXHR, textStatus, errorThrown){
                alert(errorThrown);
            }
        });

        setTimeout('$("#goBtn").removeAttr("Disabled")', 3000);
    });

    $('#submit').click(function(){

        $(this).attr("disabled", "disabled");

        $.ajax({
            url: "libraries/php/getGeolocation.php",
            type: 'POST',
            dataType: 'json',
            data: {
                q: $('#coordinatesInput').val(),
                lang: $('#languageInput').val()
            },
            success: function(result){

                console.log(result);

                if(result.status.code == 200){
                    $('#table2').css("visibility", "visible");
                    $('#formatted').html(result['results'][0]['formatted']);
                    $('#calls').html(2500 - result['rate']['remaining']);
                }
            },
            error: function(jqXHR, textStatus, errorThrown){
                alert(`Error has been thrown: ${textStatus}`);
            }
        });

        setTimeout('$("#submit").removeAttr("Disabled")', 3000);

    });
