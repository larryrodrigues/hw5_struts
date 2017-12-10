<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <title>George Mason University Department of Computer Science Prospective Student Survey</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/jquery-ui.min.css"/>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/lrhw4_styles.css"/>
        <script src="${pageContext.request.contextPath}/external/jquery/jquery.js"></script>
        <script src="${pageContext.request.contextPath}/jquery-ui.min.js"></script>
        <script type="text/JavaScript">              
            function greetUser() {
                var now = new Date(); // current date
                var hour = now.getHours(); // current hour 0 - 23
                var name;
                
                if( document.cookie){
                    var myCookie = unescape(document.cookie);
                    var cookieTokens = myCookie.split("=");
                    name = cookieTokens[1];
                } else {
                    name = getName(false);
                }
                if ( name != null && name != "null" ) {
                    name = name.trim();
                } else {
                    name = "";
                }
                var whdr = document.getElementById("welcome_header");
                var hdrText = "";
                if (whdr != null) {
                    if ( hour < 12 ) {
                        // determine whether it is morning
                        hdrText = hdrText + "Good Morning, "+name;
                    } else {
                        hour = hour - 12; // convert from 24-hour clock to PM time
                        // determine whether it is afternoon or evening
                        if ( hour < 6 ){
                            hdrText = hdrText + "Good Afternoon, "+name;
                        }else{
                           hdrText = hdrText + "Good Evening, "+name;
                        }
                    } // end else
                    hdrText = hdrText + ".&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i>Not "+name+"? Please <a class=\"float_button\" onclick=\"getDifferentName()\">click here</a></i>.";
                    whdr.innerHTML = hdrText;
                }
            }
            
            function getName(pageRefresh) {
                pageRefresh = !!pageRefresh;
                var name = window.prompt( "Please enter your name.");
                if( name != null &&  name.trim() != "null" && name.trim() != "") {
                    expDate = new Date();
                    expDate.setDate(expDate.getDate() + 14); 
                    expires = expDate.toGMTString();
                    document.cookie = "name="+escape(name)+"; expires="+expires;
                    if( pageRefresh ){
                         window.location.reload(true);
                        // $(document).load("hw3_cssurvey.html");
                    }
                } else {
                    if( !pageRefresh ) {
                        // User clicked "Not Me" and hit cancel or no name enterred.
                        $( "#modal-title" ).text("Data Validation Error:");
                        $( "#modal-error-message" ).html("Please enter a valid name");
                        $( "#dialog-message" ).dialog({
                            modal: true,
                            buttons: {
                                Ok: function() {
                                    $( this ).dialog( "close" );
                                }
                            }
                        });
                    }
                }
                return name;
            }
            
            function getDifferentName() {
              getName(true);    
            }
            
            function pageSetup(){
                greetUser();
            }
            
            function validateSurveyForm() {
                var retval = true;
                // var surveyForm = document.forms["surveyForm"];
                var nameFirstLast = document.getElementById("txt_namefirstlast").value;
                var address = document.getElementById("txt_streetaddress").value;
                var zipcode = document.getElementById("txt_zipcode").value;
                var emailAddress = document.getElementById("txt_email").value;
                var radInterest = document.forms["surveyForm"]["interestind"];
                var chkLike = document.forms["surveyForm"]["campuslike"];
                var errorStr = "";               
                var dataset = document.getElementById("avg_max_set");
                
                if( dataset != null ){
                    var raw = dataset.value;
                    // alert("Got: "+raw);
                    // test regex ^(100|[1-9]|[1-9][0-9])+(,(100|[1-9]|[1-9][0-9])+){9,}$
                    var patt = new RegExp("^(100|[1-9]|[1-9][0-9])+(,(100|[1-9]|[1-9][0-9])+){9,}$");
                    var res = patt.test(raw);
                    if(!res){
                        errorStr = errorStr  = "Data set must match at least 10 comma separated values ranging 1 through 100.";
                    }
                }
                
                if( nameFirstLast != null && nameFirstLast != "null" && nameFirstLast.trim() != "" ){
                    val = nameFirstLast.trim();
                    // var re = new RegExp("^[a-zA-Z\s]*$");
                    // var res = re.test(val);
                    valid = /^[a-zA-Z\s]*$/.test(val)
                    if (!valid){
                        errorStr = errorStr + "Name must be only letters and spaces.<br>";
                        document.getElementById("txt_namefirstlast").value = "";
                    }
                } else {
                    errorStr = errorStr + "You must supply a valid username.<br>";
                }
                
                if( address != null && address != "null" && address.trim() != "" ){
                    val = address.trim();
                    // var re = new RegExp("^[a-zA-Z\s]*$");
                    // var res = re.test(val);
                    valid = /^[a-zA-Z0-9\s]*$/.test(val)
                    if (!valid){
                        errorStr = errorStr + "Street address must be only letters, numbers, and spaces.<br>";
                        document.getElementById("txt_streetaddress").value = "";
                    }
                } else {
                    errorStr = errorStr + "You must supply a valid address.<br>";
                }
                // var isValidZip = /(^\d{5}$)|(^\d{5}-\d{4}$)/.test("90210");
                // Not Required
                if( zipcode != null && zipcode != "null" && zipcode.trim() != "" ){
                    val = zipcode.trim();
                    valid = /(^\d{5}$)/.test(val)
                    if (!valid){
                        errorStr = errorStr + "Zipcode must be 5 digits.<br>";
                        document.getElementById("txt_zipcode").value = "";
                    }
                }
                // /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/
                // Pattern used from https://www.w3resource.com/javascript/form/email-validation.php
                if( emailAddress != null && emailAddress != "null" && emailAddress.trim() != "" ){
                    val = emailAddress.trim();
                    // var re = new RegExp("^[a-zA-Z\s]*$");
                    // var res = re.test(val);
                    valid = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(val)
                    if (!valid){
                        errorStr = errorStr + "Please enter a valid email address.<br>";
                        document.getElementById("txt_email").value = "";
                    }
                } else {
                    errorStr = errorStr + "You must supply a valid email address.<br>";
                }
                
                if( chkLike != null ) {
                    chkCount = 0;
                    for(i=0; (i<chkLike.length && chkCount<2); i++){
                        if( chkLike[i].checked){
                            chkCount++;
                        }
                    }
                    if(chkCount < 2){
                        errorStr = errorStr + "Please select at least 2 items you liked about the university.<br>";
                    }
                }
                
                if( radInterest != null ){
                    checked = false;
                    for(i=0; (i<radInterest.length && !checked); i++){
                        if( radInterest[i].checked){
                            checked = true;
                        }
                    }
                    if(!checked){
                        errorStr = errorStr + "You must select a University interest item.<br>";
                    }
                }
                
                if( errorStr != null && errorStr != "" ){
                    retval = false;
                    $( "#modal-title" ).html("Data Validation Error:");
                    $( "#modal-error-message" ).html(errorStr);
                    $( "#dialog-message" ).dialog({
                        modal: true,
                        buttons: {
                            Ok: function() {
                                $( this ).dialog( "close" );
                            }
                        }
                    });
                }
                return retval;
            }
            
            function zipReqStateChange() {
                            if (xhttp.readyState == 4 && xhttp.status == 200) {
                                // document.getElementById("ta_comments").innerHTML = this.responseText;
                                json_zips = xhttp.responseText;
                                zips_array = xhttp.responseText;
                            }
            }
            
            function zipcodeChange() {
                var zipcode = document.getElementById("txt_zipcode").value;
                if( zipcode != null && zipcode != "null" && zipcode.trim() != "" ){
                    val = zipcode.trim();
                    // test valid zip code
                    if( val.length == 5 ){
                        var xhttp = new XMLHttpRequest();
                        // get city state
                        // xhttp.onreadystatechange = zipReqStateChange;
                        xhttp.onreadystatechange = function() {
                            if (this.readyState == 4 && this.status == 200) {
                                var city = document.getElementById("sp_city");
                                var state = document.getElementById("sp_state");
                                json_zips = this.responseText;
                                zips_array = JSON.parse(json_zips);
                                zl = zips_array["zipcodes"];
                                found = false;
                                for(i=0;i < zl.length;i++){
                                    var o = zl[i];
                                    lv = o.zip;
                                    if( lv == val){
                                        city.value = o.city;
                                        state.value = o.state;
                                        found = true;
                                    }
                                }
                                if( !found ){
                                    city.innerHTML = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
                                    state.innerHTML = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
                                }
                            }
                        };
                        xhttp.open("GET", "hw3_zipcodes.json", true);
                        xhttp.send();
                    }
                }
            }
        </script>
        <script type="text/JavaScript">        
            $(document).ready(function() {
                $( document ).tooltip();
                pageSetup();
            });
        </script>
    </head>
    <body class="welcome">
<div class="bkg">
<div class="topnav">
  <a class="active" href="./hw2_csinfo.html">CS Department Information</a>
  <a href="./index.html">Larry Rodrigues Class Web Page</a>
  <a href="#contact">Contact Larry Rodrigues</a>
  <a href="#survey">Survey</a>
</div>
<div id="dialog-message" title="Form Validation Results">
  <p>
    <p id="modal-title"></p>
    <p id="modal-error-message"></p>
</div>
        <h1 class="top_header">George Mason University Department of Computer Science Prospective Student Survey</h1>
        <h3 class="welcome" id="welcome_header">Welcome</h3>
        <h2>Please help us better understand our prospect Computer Science students by completing this survey</h2>
        <form action="surveysubmit" name ="surveyForm" method="post" autocomplete="on" onsubmit="return validateSurveyForm()">
            <input type="hidden" name="action" value="store-survey">
            <input type="hidden" name="seqId" value="-1">
        <table align="center" width="100%">
            <tr>
                <td align="left">Username:</td>
                <td align="left">Email:</td>
                <td align="left">Name (First and Last):</td>
            </tr>
            <tr>
                <td align="left"><input type="text" name="username"  id="txt_username"  size="20"  autocomplete="on" maxlength="20" autofocus="autofocus" required /></td>
                <td align="left"><input required type="email" name="email" id="txt_email" size="30"  autocomplete="on" maxlength="50" placeholder="name@domain-name.com" /></td>
                <td align="left"><input required type="text" name="namefirstlast" id="txt_namefirstlast" size="30"  autocomplete="on" maxlength="50"/></td>
            </tr>
            <tr>
                <td align="left">Street Address:</td>
                <td align="left">City:</td>
                <td align="left">State:</td>
            </tr>
            <tr>
                <td align="left"><input type="text" name="streetaddress" id="txt_streetaddress" size="50"  autocomplete="on" maxlength="50"/></td>
                <td align="left"><input type="text" name="city"  id="sp_city"  size="20" autocomplete="on" maxlength="20" required /></td>
                <td align="left"><input type="text" name="state"  id="sp_state" size="20" autocomplete="on" maxlength="20" required /></td>
            </tr>
            <tr>
                <td align="left">Zip:</td>
                <td align="left">Phone Number:</td>
                <td align="left">Home Page:</td>
            </tr>
            <tr>
                <td align="left"><input type="text" name="zipcode" id="txt_zipcode" pattern="\(\[0-9]{5}\)" placeholder="#####" size="10"  autocomplete="on" maxlength="5" onblur="zipcodeChange();"/></td>
                <td align="left"><input type="tel" name="phonenumber" id="txt_phonenumber" pattern="\(\d{3}\)+\d{3}-\d{4}" placeholder="(###) ###-####"/></td>
                <td align="left"><input type="url" id="txt_homepageurl" name="homepageurl"  placeholder="http://domain-name.com" size="30"  autocomplete="on" maxlength="50"/></td>
            </tr>
            <tr>
                <td align="left">Date of Survey:</td>
                <td align="left">High school graduation month:</td>
                <td align="left">High school graduation year:</td>
            </tr>
            <tr>
                <td align="left"><input type="date" id="txt_surveydate" name="surveydate" pattern="yyyy-mm-dd" placeholder="yyyy-mm-dd"/></td>
                <td align="left"><input type="text" name="hsgradmonth" id="txt_hsgradmonth" placeholder="Select a Month" list="months"/>
                    <datalist id="months">
                        <option value="January"></option>
                        <option value="February"></option>
                        <option value="March"></option>
                        <option value="April"></option>
                        <option value="May"></option>
                        <option value="June"></option>
                        <option value="July"></option>
                        <option value="August"></option>
                        <option value="September"></option>
                        <option value="October"></option>
                        <option value="November"></option>
                        <option value="December"></option>                        
                    </datalist></td>
                <td align="left"><input type="text" name="gradyear" id="txt_gradyear" size="10" maxlength="4" placeholder="Year YYYY" pattern="[1,2][0-9][0-9][0-9]" /></td>
            </tr>
            <tr>
                <td align="left" colspan="3">Student ID:</td>
            </tr>
            <tr>
                <td align="left" colspan="3"><input type="text" name="studentid"  id="txt_studentid"  size="20"  autocomplete="on" maxlength="20" autofocus="autofocus" required /></td>
            </tr>
        </table>
        <br />
        <table class="survey_option_table" align="center" width="100%">
            <tr class="survey_option_row">
                <th align="left" class="survey_option">What did you like best about the campus?</th>
                <th align="left" class="survey_option">How did you become interested in the University?</th>
                <th align="left" class="survey_option">How likely are you to recommend this school?</th>
            </tr>
            <tr>
                <td align="left" class="survey_option">
                      <input type="checkbox" name="campuslike" id="students" value="students" />Students <br />
                      <input type="checkbox" name="campuslike" id="location" value="location" />Location<br />
                      <input type="checkbox" name="campuslike" id="campus" value="campus" />Campus<br />
                      <input type="checkbox" name="campuslike" id="atmosphere" value="atmosphere" />Atmosphere<br />
                      <input type="checkbox" name="campuslike" id="dormrooms" value="dormrooms" />Dorm Rooms<br />
                      <input type="checkbox" name="campuslike" id="sports" value="sports" />Sports<br />
                </td>
                <td align="left" class="survey_option">
                      <input type="radio" name="interestind" id="friends" value="friends" />Friends<br />
                      <input type="radio" name="interestind" id="television" value="television" />Television<br />
                      <input type="radio" name="interestind" id="internet" value="internet" />Internet<br />
                      <input type="radio" name="interestind" id="other" value="other" />Other<br />
                </td>
                <td align="left" class="survey_option">
                    <select name="likelyrecommend" id="sel_likelyrecommend">
                        <option value="verylikely">Very Likely</option>
                        <option value="likely">Likely</option>
                        <option value="unlikely">Unlikely</option>
                    </select>
                </td>
            </tr>
        </table>
        <table class="movie_rating_table" align="center" width="100%">
            <tr class="movie_rating_row">
                <th align="left" class="survey_option">Please select a movie title to rate.</th>
                <th align="left" class="survey_option">Please select a rating for the movie.</th>
                <th align="left" class="survey_option">Please enter 10 or more comma separated numbers.</th>
            </tr>
            <tr class="movie_rating_row">
        <td><input type="hidden" name="userid" id="hid_userid" value="1"/>
        <select name="movieid" id="sel_movieid">
        <option value="-1">----------</option>
	<option value="1">Sweet Home Alabama</option>
	<option value="2">Godzilla</option>
	<option value="3">Raising Arizona</option>
	<option value="4">The Godfather</option>
	<option value="5">Pulp Fiction</option>
	<option value="6">Alien</option>
	<option value="7">Harry Potter and the Sorcerers Stone</option>
	<option value="8">Lion In Winter</option>
	<option value="9">Red</option>
        <option value="10">Life Of Pie</option>
        </select></td>
        <td><select name="movierating" id="sel_movierating">
        <option value="-1">----------</option>
	<option value="1">1 -- terrible</option>
	<option value="2">2</option>
	<option value="3">3</option>
	<option value="4">4</option>
	<option value="5">5</option>
	<option value="6">6</option>
	<option value="7">7</option>
	<option value="8">8</option>
	<option value="9">9</option>
        <option value="10">10 -- perfect</option>
                    </select>&nbsp;[1 (terrible)... 10 (perfect)]&nbsp;
                    <input type="hidden" name="ratingtstamp" id="hid_ratingtimestamp"/>
                            </td>
        <td align="left"><input id="avg_max_set" name="avg_max_data" type="text" size="40" placeholder="1,2,3.....N" pattern="^(100|[1-9]|[1-9][0-9])+(,(100|[1-9]|[1-9][0-9])+){9,}$" />&nbsp;<span class="error_text" id="avg_max_error"></span></td>
            </tr>
        </table>
        <table class="comments_table" align="center" width="100%">
            <tr><td align="left">Please enter any additional comments.</td></tr>
            <tr><td align="left"><textarea rows="10" cols="70" name="comments" id="ta_comments">Enter your comments here...</textarea></td></tr>
        </table>
        <table align="center" width="100%">
            <tr><td align="center"><input type="submit" value="Submit now" />&nbsp;<input type="reset" value="Reset Form Fields"></td></tr>
        </table>
        </form>
        <div class="fixed_bottom_right"><a href="https://www2.gmu.edu/" name="mason-logo"><img title="Please visit http://www.gmu.edu for more information" src="mason-logo-green.png"/></a></div>
</div>
    </body>
</html>

