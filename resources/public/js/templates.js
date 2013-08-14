this["JST"] = this["JST"] || {};

this["JST"]["src/Templates/Chart-LoanCalculator.handlebars"] = Handlebars.template(function (Handlebars,depth0,helpers,partials,data) {
  this.compilerInfo = [4,'>= 1.0.0'];
helpers = this.merge(helpers, Handlebars.helpers); data = data || {};
  var buffer = "";


  buffer += "\n<div id=\"chart\" style=\"width:100%\">\n	<div class=\"update\" style=\"background:#333\"> UPDATE YO SELF</div>\n	<svg style=\"height:500px\"></svg>\n</div>";
  return buffer;
  });

this["JST"]["src/Templates/Dashboard-Loading.handlebars"] = Handlebars.template(function (Handlebars,depth0,helpers,partials,data) {
  this.compilerInfo = [4,'>= 1.0.0'];
helpers = this.merge(helpers, Handlebars.helpers); data = data || {};
  var buffer = "";


  buffer += "\n\n\nDASHBOARD LOADING";
  return buffer;
  });

this["JST"]["src/Templates/Dashboard-LoanSummary.handlebars"] = Handlebars.template(function (Handlebars,depth0,helpers,partials,data) {
  this.compilerInfo = [4,'>= 1.0.0'];
helpers = this.merge(helpers, Handlebars.helpers); data = data || {};
  var buffer = "", stack1, functionType="function", escapeExpression=this.escapeExpression, self=this;

function program1(depth0,data) {
  
  var buffer = "", stack1, stack2;
  buffer += "\n<div>\n<span>"
    + escapeExpression(((stack1 = depth0.name),typeof stack1 === functionType ? stack1.apply(depth0) : stack1))
    + "</span>\n<span>"
    + escapeExpression(((stack1 = depth0.account_name),typeof stack1 === functionType ? stack1.apply(depth0) : stack1))
    + "</span>\n<span>"
    + escapeExpression(((stack1 = depth0.minimum_payment),typeof stack1 === functionType ? stack1.apply(depth0) : stack1))
    + "</span>\n<span>";
  if (stack2 = helpers.total) { stack2 = stack2.call(depth0, {hash:{},data:data}); }
  else { stack2 = depth0.total; stack2 = typeof stack2 === functionType ? stack2.apply(depth0) : stack2; }
  buffer += escapeExpression(stack2)
    + "</span>\n<hr>\n<span>"
    + escapeExpression(((stack1 = depth0.original_balance),typeof stack1 === functionType ? stack1.apply(depth0) : stack1))
    + "</span>\n<span>"
    + escapeExpression(((stack1 = depth0.balance),typeof stack1 === functionType ? stack1.apply(depth0) : stack1))
    + "</span>\n<span>"
    + escapeExpression(((stack1 = depth0.current_payment),typeof stack1 === functionType ? stack1.apply(depth0) : stack1))
    + "</span>\n<span>";
  if (stack2 = helpers.totalInterest) { stack2 = stack2.call(depth0, {hash:{},data:data}); }
  else { stack2 = depth0.totalInterest; stack2 = typeof stack2 === functionType ? stack2.apply(depth0) : stack2; }
  buffer += escapeExpression(stack2)
    + "</span>\n<span>";
  if (stack2 = helpers.totalPrinciple) { stack2 = stack2.call(depth0, {hash:{},data:data}); }
  else { stack2 = depth0.totalPrinciple; stack2 = typeof stack2 === functionType ? stack2.apply(depth0) : stack2; }
  buffer += escapeExpression(stack2)
    + "</span>\n<span>";
  if (stack2 = helpers.totalPaid) { stack2 = stack2.call(depth0, {hash:{},data:data}); }
  else { stack2 = depth0.totalPaid; stack2 = typeof stack2 === functionType ? stack2.apply(depth0) : stack2; }
  buffer += escapeExpression(stack2)
    + "</span>\n<span>"
    + escapeExpression(((stack1 = depth0.loan_type_name),typeof stack1 === functionType ? stack1.apply(depth0) : stack1))
    + "</span>\n<span>"
    + escapeExpression(((stack1 = depth0.interest_rate),typeof stack1 === functionType ? stack1.apply(depth0) : stack1))
    + "</span>\n\n</div>\n";
  return buffer;
  }

  stack1 = helpers.each.call(depth0, depth0.loans, {hash:{},inverse:self.noop,fn:self.program(1, program1, data),data:data});
  if(stack1 || stack1 === 0) { buffer += stack1; }
  buffer += "\n\n\nSAVINGS:";
  return buffer;
  });

this["JST"]["src/Templates/Dashboard-Sidenav.handlebars"] = Handlebars.template(function (Handlebars,depth0,helpers,partials,data) {
  this.compilerInfo = [4,'>= 1.0.0'];
helpers = this.merge(helpers, Handlebars.helpers); data = data || {};
  var buffer = "", stack1, functionType="function", escapeExpression=this.escapeExpression, self=this;

function program1(depth0,data) {
  
  var buffer = "", stack1;
  buffer += "\n		<li class=\"active\"><a href=\"#\">";
  if (stack1 = helpers.name) { stack1 = stack1.call(depth0, {hash:{},data:data}); }
  else { stack1 = depth0.name; stack1 = typeof stack1 === functionType ? stack1.apply(depth0) : stack1; }
  buffer += escapeExpression(stack1)
    + "</a></li>\n	";
  return buffer;
  }

  buffer += "<h2>Accounts</h2>\n<ul class=\"nav nav-pills nav-stacked\">\n	<li><a href=\"#\">AGGREGATE</a></li>\n	";
  stack1 = helpers.each.call(depth0, depth0.loans, {hash:{},inverse:self.noop,fn:self.program(1, program1, data),data:data});
  if(stack1 || stack1 === 0) { buffer += stack1; }
  buffer += "\n</ul>";
  return buffer;
  });

this["JST"]["src/Templates/Dashboard.handlebars"] = Handlebars.template(function (Handlebars,depth0,helpers,partials,data) {
  this.compilerInfo = [4,'>= 1.0.0'];
helpers = this.merge(helpers, Handlebars.helpers); data = data || {};
  var buffer = "";


  buffer += "<div class=\"container-fluid\">\n  <div class=\"row-fluid\">\n  	<div class=\"span4\" style=\"height:75px;\">\n  		<h3>Loan Type</h3>\n  			Federal\n  	</div>\n  	<div class=\"span4\" style=\"height:75px;\">\n  		<h3>Loan Status</h3>\n  			Current\n  	</div>\n  	<div class=\"span4\" style=\"height:75px;\">\n  		<h3>Monthly Payment</h3>\n  			$385.30\n  	</div>\n  	<hr class=\"featurette-divider\">\n  </div>\n\n  <div class=\"row-fluid\">\n    <div id=\"dash-left\"   class=\"span2\"><div class=\"full-width\" style=\"padding:20px;\">Sidebar</div></div>\n    <div id=\"dash-middle\" class=\"span10\"><div class=\"full-width\" style=\"padding:20px;\">Main Content</div></div>\n    "
    + "\n  </div>\n  <div class=\"row-fluid\">\n    <div id=\"info\" class=\"span12\"><div class=\"full-width\" style=\"padding:320px;\">LOTS OF INFORMATION HERE</div></div>\n    "
    + "\n    "
    + "\n  </div>\n</div>";
  return buffer;
  });

this["JST"]["src/Templates/Error-AjaxRead.handlebars"] = Handlebars.template(function (Handlebars,depth0,helpers,partials,data) {
  this.compilerInfo = [4,'>= 1.0.0'];
helpers = this.merge(helpers, Handlebars.helpers); data = data || {};
  var buffer = "", stack1, functionType="function", escapeExpression=this.escapeExpression, self=this;

function program1(depth0,data) {
  
  var buffer = "", stack1;
  buffer += "\n	<h3>Error trying to read the "
    + escapeExpression(((stack1 = depth0.name),typeof stack1 === functionType ? stack1.apply(depth0) : stack1))
    + " resource at "
    + escapeExpression(((stack1 = ((stack1 = depth0.resource),stack1 == null || stack1 === false ? stack1 : stack1.url)),typeof stack1 === functionType ? stack1.apply(depth0) : stack1))
    + "</h3>\n";
  return buffer;
  }

  buffer += "Error Reading!\n\n";
  stack1 = helpers.each.call(depth0, depth0.errors, {hash:{},inverse:self.noop,fn:self.program(1, program1, data),data:data});
  if(stack1 || stack1 === 0) { buffer += stack1; }
  return buffer;
  });

this["JST"]["src/Templates/Error-PathNotFound.handlebars"] = Handlebars.template(function (Handlebars,depth0,helpers,partials,data) {
  this.compilerInfo = [4,'>= 1.0.0'];
helpers = this.merge(helpers, Handlebars.helpers); data = data || {};
  


  return "INVALID 404";
  });

this["JST"]["src/Templates/Footer.handlebars"] = Handlebars.template(function (Handlebars,depth0,helpers,partials,data) {
  this.compilerInfo = [4,'>= 1.0.0'];
helpers = this.merge(helpers, Handlebars.helpers); data = data || {};
  


  return "<!-- REAL FOOTER -->\n<footer class=\"container center\">\n    <div class=\"row-fluid\">\n    <div class=\"span2 offset1 nowrap\">\n        <h5>CONTACT:</h5>\n        <div itemscope itemtype=\"http://schema.org/Organization\">\n            <p>\n                <span itemprop=\"streetAddress\">90 Clinton St.</span> <br/>\n                <span itemprop=\"addressLocality\">New York City</span>, <span itemprop=\"addressRegion\">NY</span> 10002 <br/>\n                <a href=\"tel:867.5309\">867.5309</a>\n            </p>\n        </div>\n\n        <ul class=\"arrow-link\">\n            <li> \n                <h5 class=\"\">\n                <i class=\"icon-angle-right\"></i>\n                <a id=\"map-direction\" href=\"#Map\" class=\"\">\n                    Map & Directions\n                </a>\n                </h5>\n            </li>\n            <li>\n                <h5 class=\"\">\n                <i class=\"icon-angle-right\"></i>\n                <a id=\"parking-options\" href=\"#Map\" class=\"\">\n                    Parking options\n                </a>\n                </h5>\n            </li>\n        </ul>\n\n        <p style=\"margin:0;\">\n            Get in touch:\n        </p>\n        <ul class=\"arrow-link\">\n            <li> \n                <h5 style=\"margin-top: 0;\" >\n                  <i class=\"icon-angle-right\"></i>\n                  <a class=\"\" href=\"mailto:hello@studentlendingproject.com\">info@studentlendingproject.com</a>\n                </h5>\n            </li>\n        </ul>\n    </div>\n    <div class=\"span4 offset1 tweets\">\n        <h5>TWITTER:</h5>\n          <div class=\"item\">\n            <p>\n                @sitchenko Thanks for spreading the word, Sitch!\n                <span class=\"moment black-letter\">\n                    <a href=\"http://twitter.com/sitchenko/status\" target=\"_blank\">\n                      7 hours  ago.\n                    </a>\n                </span>\n            </p>\n        </div>\n          <div class=\"item\">\n            <p>\n                @abKors Student Lending Project is the best!\n                <span class=\"moment black-letter\">\n                    <a href=\"http://twitter.com/abKors/status/\" target=\"_blank\">\n                    1 day  ago.\n                    </a>\n                </span>\n            </p>\n        </div>\n        <p class=\"social-icons\">\n          <a href=\"https://www.facebook.com/studentlendingproject\" target=\"_blank\"><i class=\"icon-facebook\"></i></a>\n          <a href=\"https://twitter.com/studentlendingproject\" target=\"_blank\"><i class=\"icon-twitter\"></i></a>\n          <a href=\"http://www.linkedin.com/company/studentlendingproject\" target=\"_blank\"><i class=\"icon-linkedin\"></i></a>\n        </p>\n    </div>\n    <div class=\"span3 offset1 \">\n        <ul class=\"arrow-link nowrap\">\n            <li class=\"\"> \n                <h5>\n                    <i class=\"icon-angle-right\"></i>\n                    <a href=\"/careers\" target=\"_self\">Careers</a> \n                </h5>\n            </li>\n        </ul>\n        <div class=\"row-fluid newsletter\">\n            <div id=\"newsletterSignup\" class=\"span12\">\n                <h5 class=\"black-letter title\">NEWSLETTER SIGNUP</h5>\n                <input type=\"text\" style=\"display:none;\" name=\"email123\" value=\"AF#w}BIj462OMgZ[y^%#WX0BauiGDutYs\"/>\n                <input class=\"signup hit-enter\" type=\"text\" name=\"email\" placeholder=\"ENTER YOUR EMAIL\" data-target=\".newsletter .submit\"/>\n                <br/>\n                <input type=\"button\" class=\"btn block submit light-grey-bg\" value=\"Submit\" />\n            </div>\n        </div>\n        <div class=\"newsletter-confirm hide\" style=\"white-space: normal;\"></div>\n\n        <div class=\"legal\">\n          <p class=\"small-note\">\n            &COPY;2013 Student Lending Project. All Rights Reserved. \n            <br/>\n            <a href=\"#privacy\" class=\"\">Privacy</a> | <a href=\"#terms-conditions\" class=\"\">Terms & Conditions</a>\n          </p>\n        </div>\n      </div>\n\n      <!-- <div class=\"span5\"></div> -->\n\n    </div>\n</footer>";
  });

this["JST"]["src/Templates/Header.handlebars"] = Handlebars.template(function (Handlebars,depth0,helpers,partials,data) {
  this.compilerInfo = [4,'>= 1.0.0'];
helpers = this.merge(helpers, Handlebars.helpers); data = data || {};
  var buffer = "";


  buffer += "<!-- INITIAL BACKGROUND \n================================================== -->\n<div id=\"landing\" class=\"container-fluid\" style=\"padding:0;\" >\n    <div class=\"row-fluid\">\n      <div class=\"span12\">\n        <div class=\"full-width\">\n          <img src=\"http://cdn.studentlendingproject.com/img/colored-pencils-bg.jpg\" alt=\"Student Lending Project\" class=\"full-width\" /> \n        </div>\n      </div>\n    </div>\n</div> \n\n<!-- NAVBAR\n================================================== -->\n<div id=\"navbar\" class=\"container-fluid navbar-shadow\">\n    <div class=\"container navbar-wrapper\" style=\"padding:0;\">\n        \n        <!-- HAD HOME CLASS -->\n        <div class=\"navbar\">\n            <div class=\"navbar-inner\">\n                <div class=\"container\">\n                    <a class=\"btn btn-navbar\" data-toggle=\"collapse\" data-target=\".nav-collapse\"></a>\n                    <div class=\"row\">\n                        <div class=\"span2 offset1\"> \n                            <a class=\"brand active \" href=\"http://www.StudentLendingProject.com\">\n                                <img src=\"http://cdn.studentlendingproject.com/img/header-logo.png\" data-at2x=\"http://cdn.studentlendingproject.com/img/header-logo@2x.png\" alt=\"student lending project\"/>\n                            </a>\n                        </div>\n                        <div class=\"nav-collapse collapse\">\n                            <div class=\"span2\"></div>\n                            <ul id=\"menu-TopMenu\" class=\"nav span7\">\n                              <li><a class=\"text-center\" href=\"#Dashboard\">Dashboard</a></li>\n                              <li><a class=\"text-center\" href=\"#Learn\">Learn</a></li>\n                              <li><a class=\"text-center\" href=\"#Marketing\">Marketing</a></li>\n                              <li><a class=\"text-center\" href=\"#Signup\">Signup</a></li>\n                              <li><a class=\"text-center\" href=\"#Login\">Login</a></li>\n                              "
    + "\n                            </ul>\n                        </div>\n                    </div>\n                </div>\n            </div>\n        </div>\n    </div>\n</div>";
  return buffer;
  });

this["JST"]["src/Templates/Linking/linking.handlebars"] = Handlebars.template(function (Handlebars,depth0,helpers,partials,data) {
  this.compilerInfo = [4,'>= 1.0.0'];
helpers = this.merge(helpers, Handlebars.helpers); data = data || {};
  


  return "<div class=\"linkingModal-container\">\n	<a class=\"float-r close\" id=\"closeLinking\" data-dismiss=\"modal\" href=\"#\">×</a>\n	<div id=\"linkingModal_left\" style=\"padding:15px;position:relative;\"></div>\n	<div class=\"clearfix\"></div>\n</div>\n<div id=\"account_drawer_mask\">\n	<div id=\"statusBar_container\"></div>\n	<div id=\"account_drawer\"></div>\n</div>\n<div id=\"linking-errorTooltip\" style=\"display:none;\">\n	<div class=\"caret\"></div>\n	<div class=\"bgCaret\"></div>\n	<div class=\"errorTooltip-icon\"></div>\n	<div class=\"errorTooltip-text\">\n		There has been an error connecting to<br>\n		<span id=\"errorTooltip-number\"></span>\n	</div>\n</div>";
  });

this["JST"]["src/Templates/Login.handlebars"] = Handlebars.template(function (Handlebars,depth0,helpers,partials,data) {
  this.compilerInfo = [4,'>= 1.0.0'];
helpers = this.merge(helpers, Handlebars.helpers); data = data || {};
  


  return "<div class=\"container-fluid pad-form\">\n  <div class=\"row-fluid\">\n    <div class=\"span4 offset4\">\n      <div class=\"well\">\n        <legend>Log into SLP</legend>\n        <form method=\"POST\" action=\"\" accept-charset=\"UTF-8\">\n\n            <div class=\"alert alert-error hidden\">\n                <a class=\"close\" data-dismiss=\"alert\" href=\"#\">x</a>Incorrect Username or Password!\n            </div>\n            <input class=\"span12\" placeholder=\"Username\" type=\"text\" name=\"username\">\n            <input class=\"span12\" placeholder=\"Password\" type=\"password\" name=\"password\"> \n            <label class=\"checkbox\">\n                <input type=\"checkbox\" name=\"remember\" value=\"1\"> Remember Me\n            </label>\n            <button id=\"login-btn\" class=\"btn-info btn\">Login</button>\n        </form>    \n      </div>\n    </div>\n  </div>\n</div>	";
  });

this["JST"]["src/Templates/Marketing.handlebars"] = Handlebars.template(function (Handlebars,depth0,helpers,partials,data) {
  this.compilerInfo = [4,'>= 1.0.0'];
helpers = this.merge(helpers, Handlebars.helpers); data = data || {};
  


  return "        <!-- Carousel\n        ================================================== -->\n        <div id=\"myCarousel\" class=\"carousel slide\">\n          <div class=\"carousel-inner\">\n            <div class=\"item active\">\n              <img src=\"http://cdn.studentlendingproject.com/img/slide-01-sized.jpg\" alt=\"\" class=\"\">\n              <div class=\"container\">\n                <div class=\"carousel-caption\">\n                  <h1>Pay for your future not your past</h1>\n                  <!-- <p class=\"lead\">Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.</p> -->\n                </div>\n              </div>\n            </div>\n            <div class=\"item\">\n              <img src=\"http://cdn.studentlendingproject.com/img/slide-02-sized.jpg\" alt=\"\" class=\"\">\n              <div class=\"container\">\n                <div class=\"carousel-caption\">\n                  <h1>Get to where you need to be going</h1>\n                  <!-- <p class=\"lead\">Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.</p> -->\n                </div>\n              </div>\n            </div>\n            <div class=\"item\">\n              <img src=\"http://cdn.studentlendingproject.com/img/slide-03.jpg\" alt=\"\" class=\"\">\n              <div class=\"container\">\n                <div class=\"carousel-caption\">\n                  <h1>Solve the puzzle</h1>\n                  <!-- <p class=\"lead\">Cras justo odio, dapibus ac facilisis in, egestas eget quam. Donec id elit non mi porta gravida at eget metus. Nullam id dolor id nibh ultricies vehicula ut id elit.</p> -->\n                </div>\n              </div>\n            </div>\n          </div>\n          <a class=\"left carousel-control\" href=\"#myCarousel\" data-slide=\"prev\">&lsaquo;</a>\n          <a class=\"right carousel-control\" href=\"#myCarousel\" data-slide=\"next\">&rsaquo;</a>\n        </div><!-- /.carousel -->\n\n\n\n        <!-- Marketing messaging and featurettes\n        ================================================== -->\n        <!-- Wrap the rest of the page in another container to center all the content. -->\n\n        <div class=\"container marketing\">\n\n          <!-- Three columns of text below the carousel -->\n          <!-- \n          <div class=\"row\">\n            <div class=\"span4\">\n              <img class=\"img-circle\" src=\"http://cdn.studentlendingproject.com/img/person.png\" alt=\"\">\n              <h2>Personal</h2>\n              <p>Donec sed odio dui. Etiam porta sem malesuada magna mollis euismod. Nullam id dolor id nibh ultricies vehicula ut id elit. Morbi leo risus, porta ac consectetur ac, vestibulum at eros. Praesent commodo cursus magna, vel scelerisque nisl consectetur et.</p>\n              <p><a class=\"btn\" href=\"#\">View details &raquo;</a></p>\n            </div>\n            <div class=\"span4\">\n              <img class=\"img-circle\" src=\"http://cdn.studentlendingproject.com/img/dashboard.png\" alt=\"\">\n              <h2>Dashboard</h2>\n              <p>Duis mollis, est non commodo luctus, nisi erat porttitor ligula, eget lacinia odio sem nec elit. Cras mattis consectetur purus sit amet fermentum. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus.</p>\n              <p><a class=\"btn\" href=\"#\">View details &raquo;</a></p>\n            </div>\n            <div class=\"span4\">\n              <img class=\"img-circle\" src=\"http://cdn.studentlendingproject.com/img/graduationHat.png\" alt=\"\">\n              <h2>Refinance</h2>\n              <p>Donec sed odio dui. Cras justo odio, dapibus ac facilisis in, egestas eget quam. Vestibulum id ligula porta felis euismod semper. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus.</p>\n              <p><a class=\"btn\" href=\"#\">View details &raquo;</a></p>\n            </div>\n          </div> -->\n\n\n          <!-- START THE FEATURETTES -->\n          <!-- <hr class=\"featurette-divider\"> -->\n\n          <div class=\"featurette\">\n            <img class=\"featurette-image pull-left\" src=\"http://cdn.studentlendingproject.com/img/demystify.jpg\" alt=\"\">\n            <h2 class=\"featurette-heading\">Demystify your student loan rates & payments. <span class=\"muted\">See for yourself.</span></h2>\n            <p class=\"lead\">We work hard to provide you with the necessary tools to find the absolute best student loan rate for you.</p>\n          </div>\n\n          <hr class=\"featurette-divider\">\n\n          <div class=\"featurette\">\n            <img class=\"featurette-image pull-right\" src=\"http://cdn.studentlendingproject.com/img/totalControl.jpg\" alt=\"\">\n            <h2 class=\"featurette-heading\">Take Control. <span class=\"muted\">The move is yours.</span></h2>\n            <p class=\"lead\">Donec ullamcorper nulla non metus auctor fringilla. Vestibulum id ligula porta felis euismod semper. Praesent commodo cursus magna, vel scelerisque nisl consectetur. Fusce dapibus, tellus ac cursus commodo.</p>\n          </div>\n\n          <hr class=\"featurette-divider\">\n\n          <div class=\"featurette\">\n            <img class=\"featurette-image pull-left img-circle featurette-img-shadow\" src=\"http://cdn.studentlendingproject.com/img/takeControl.png\" alt=\"\">\n            <h2 class=\"featurette-heading\">Refresh your potential. <span class=\"muted\">It'll blow your mind.</span></h2>\n            <p class=\"lead\">Donec ullamcorper nulla non metus auctor fringilla. Vestibulum id ligula porta felis euismod semper. Praesent commodo cursus magna, vel scelerisque nisl consectetur. Fusce dapibus, tellus ac cursus commodo.</p>\n          </div>\n\n          <hr class=\"featurette-divider\">\n\n          <div style=\"height:60px;\"></div>\n\n          <!-- /END THE FEATURETTES -->\n\n          <!-- FOOTER -->\n          <!-- <footer>\n            <p class=\"pull-right\"><a href=\"#\">Back to top</a></p>\n            <p>&copy; 2013 Company, Inc. &middot; <a href=\"#\">Privacy</a> &middot; <a href=\"#\">Terms</a></p>\n          </footer> -->\n\n        </div><!-- /.container -->";
  });

this["JST"]["src/Templates/Signup-AdvancedRegistration.handlebars"] = Handlebars.template(function (Handlebars,depth0,helpers,partials,data) {
  this.compilerInfo = [4,'>= 1.0.0'];
helpers = this.merge(helpers, Handlebars.helpers); data = data || {};
  var buffer = "";


  buffer += "<div class=\"span4 offset4\">\n  <div class=\"well\">\n    <legend>Complete Registration</legend>\n    <form method=\"POST\" action=\"\" accept-charset=\"UTF-8\">\n\n        <div class=\"alert alert-error hidden\">\n            <a class=\"close\" data-dismiss=\"alert\" href=\"#\">x</a>Incorrect Username or Password!\n        </div>\n        "
    + "\n        <button id=\"complete-btn\" class=\"btn-info btn\">Complete</button>\n    </form>    \n  </div>\n</div>\n";
  return buffer;
  });

this["JST"]["src/Templates/Signup-BasicRegistration.handlebars"] = Handlebars.template(function (Handlebars,depth0,helpers,partials,data) {
  this.compilerInfo = [4,'>= 1.0.0'];
helpers = this.merge(helpers, Handlebars.helpers); data = data || {};
  var buffer = "";


  buffer += "<div class=\"span4 offset4\">\n  <div class=\"well\">\n    <legend>Sign-up for SLP</legend>\n    <form method=\"POST\" action=\"\" accept-charset=\"UTF-8\">\n\n        <div class=\"alert alert-error hidden\">\n            <a class=\"close\" data-dismiss=\"alert\" href=\"#\">x</a>Incorrect Username or Password!\n        </div>\n        <input class=\"span12\" placeholder=\"Username\" type=\"text\" name=\"username\">\n        <input class=\"span12\" placeholder=\"Email\" type=\"email\" name=\"email\"> \n        <input class=\"span12\" placeholder=\"Password\" type=\"password\" name=\"password\">	 	\n        "
    + "\n        <label class=\"checkbox\">\n            <input type=\"checkbox\" name=\"agrees\" value=\"1\"> Agree to <a href=\"terms\">Terms of Service</a>\n        </label>\n        <label class=\"checkbox\">\n            <input type=\"checkbox\" name=\"opt-in\" value=\"1\"> Agree to <a href=\"opt-in\">Opt-in</a>\n        </label>\n        <button id=\"signup-btn\" class=\"btn-info btn\">Signup</button>\n    </form>    \n  </div>\n</div>\n";
  return buffer;
  });

this["JST"]["src/Templates/Signup-Form.handlebars"] = Handlebars.template(function (Handlebars,depth0,helpers,partials,data) {
  this.compilerInfo = [4,'>= 1.0.0'];
helpers = this.merge(helpers, Handlebars.helpers); data = data || {};
  


  return "<div class=\"span4 offset4\">\n	<div class=\"well\">\n		<legend>Basic Registration</legend>\n	</div>\n</div>";
  });

this["JST"]["src/Templates/Signup-Loading.handlebars"] = Handlebars.template(function (Handlebars,depth0,helpers,partials,data) {
  this.compilerInfo = [4,'>= 1.0.0'];
helpers = this.merge(helpers, Handlebars.helpers); data = data || {};
  


  return "LOADING SIGNUP";
  });

this["JST"]["src/Templates/Signup.handlebars"] = Handlebars.template(function (Handlebars,depth0,helpers,partials,data) {
  this.compilerInfo = [4,'>= 1.0.0'];
helpers = this.merge(helpers, Handlebars.helpers); data = data || {};
  var buffer = "";


  buffer += "<div class=\"container-fluid pad-form\">\n	<div id=\"section1\" class=\"row-fluid\"></div>\n	"
    + "\n	"
    + "\n	<div id=\"section2\" class=\"row-fluid\"></div>\n	<div id=\"section3\" class=\"row-fluid\"></div>\n</div>";
  return buffer;
  });