<!DOCTYPE html>

<html lang="en">

    <head>

        <meta charset="utf-8">

        <title>API Calls PHP AJAX JS</title>
        <meta name="description" content="API calls using php7 javascript and AJAX">
        <meta name="author" content="Piotr Berger">
        <link rel="stylesheet" type="text/css" href="libraries/css/styles.css">

    </head>

    <body>

        <select id="countrySelect" class="select">
            <option value="AU">Australia</option>
            <option value="DE">Germany</option>
            <option value="PL">Poland</option>
        </select>

        <select id="languageSelect" class="select">
            <option value="en">English</option>
            <option value="de">Deutsch</option>
            <option value="pl">Polski</option>
        </select>

        <button id="goBtn">Go</button>


        <br>

        <table id="table1">
            <tr>
                <td align="left">Continent:</td>
                <td id="continent"> </td>
            </tr>

            <tr>
                <td align="left">Capital:</td>
                <td id="capital"></td>
            </tr>

            <tr>
                <td align="left">Languages:</td>
                <td id="languages"></td>
            </tr>

            <tr>
                <td align="left">Population:</td>
                <td id="population"></td>
            </tr>

            <tr>
                <td align="left">Area (km<sup>2</sup>):</td>
                <td id="area">     
                </td>
            </tr>
        </table>

        <form id="locationForm">
            <label for="coordinatesInput">Type coordinates</label><br>
            <input id="coordinatesInput" class="inputs" value="51.4564,0.8534"><br>
            <label for="languageInput">Type language code</label><br>
            <input id="languageInput" class="inputs" value="en">
            <input id="submit" type="submit" value="Go">
        </form>

        <br>

        <table id="table2">
            <tr>
                <td align="left">Address:</td>
                <td id="formatted"></td>
            </tr>
            <tr>
                <td align="left">Calls made:</td>
                <td id="calls"></td>
            </tr>
        </table>

        <script type="application/javascript" src="libraries/js/jquery-2.2.3.min.js"></script>
        <script type="application/javascript" src="libraries/js/script.js"></script>

        
    </body>
</html>
